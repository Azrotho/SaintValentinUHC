package fr.azrotho.svuhc.utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;

import fr.azrotho.svuhc.SVUhc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class NickManager implements Listener {
    private final Map<UUID, String> names = new HashMap<>();
    private final Map<Player, WrappedGameProfile> playerPreviousIdentities = new HashMap<>();

    public NickManager() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(SVUhc.getInstance(), PacketType.Play.Server.NAMED_ENTITY_SPAWN) {
            @Override
            public void onPacketSending(PacketEvent event) {
                UUID uuid = event.getPacket().getUUIDs().read(0);
                Player player = Bukkit.getPlayer(uuid);
                PlayerProfile profile = getEffectiveProfile(player);
                event.getPacket().getUUIDs().write(0, profile.getId());
            }
        });

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(SVUhc.getInstance(), PacketType.Play.Server.PLAYER_INFO) {
            @Override
            public void onPacketSending(PacketEvent event) {
                List<PlayerInfoData> dataList = event.getPacket().getPlayerInfoDataLists().read(0);
                for (int i = 0; i < dataList.size(); i++) {
                    PlayerInfoData data = dataList.get(i);
                    Player player = Bukkit.getPlayer(data.getProfile().getUUID());
                    if (player == null) {
                        continue;
                    }
                    PlayerProfile profile = getEffectiveProfile(player);
                    WrappedGameProfile wrappedProfile = (event.getPlayer() == player) ?
                        new WrappedGameProfile(player.getUniqueId(), profile.getName()) :
                        new WrappedGameProfile(UUID.fromString(profile.getId().toString()), profile.getName());
                    for (ProfileProperty property : profile.getProperties()) {
                        wrappedProfile.getProperties().put(
                            property.getName(),
                            new WrappedSignedProperty(property.getName(), property.getValue(), property.getSignature())
                        );
                    }
                    dataList.set(i, new PlayerInfoData(
                            wrappedProfile,
                            data.getLatency(),
                            data.getGameMode(),
                            WrappedChatComponent.fromText(wrappedProfile.getName())
                    ));
                }
                event.getPacket().getPlayerInfoDataLists().write(0, dataList);
            }
        });

        Bukkit.getPluginManager().registerEvents(this, SVUhc.getInstance());
    }

    public void nick(Player player, String name) {
        names.put(player.getUniqueId(), name);
        try {
            refresh(player);
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void refresh(Player player) throws InvocationTargetException{
        // REMOVE PLAYER FOR OTHERS
        {
            WrappedGameProfile previous = playerPreviousIdentities.remove(player);
            if (previous == null) {
                previous = new WrappedGameProfile(player.getUniqueId(), player.getName());
            }
            PlayerProfile profile = getEffectiveProfile(player);
            playerPreviousIdentities.put(player, new WrappedGameProfile(profile.getId(), profile.getName()));
    
            PacketContainer packet = new PacketContainer(PacketType.Play.Server.PLAYER_INFO);
            packet.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
            packet.getPlayerInfoDataLists().write(
                    0,
                    List.of(new PlayerInfoData(
                            previous,
                            0,
                            EnumWrappers.NativeGameMode.SURVIVAL,
                            null
                    ))
            );
    
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (p != player) {
                    try {
                        ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet.deepClone(), false);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    
        // REMOVE PLAYER FOR SELF
        {
            PacketContainer packet = new PacketContainer(PacketType.Play.Server.PLAYER_INFO);
            packet.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
            packet.getPlayerInfoDataLists().write(
                    0,
                    List.of(new PlayerInfoData(
                            new WrappedGameProfile(player.getUniqueId(), player.getName()),
                            0,
                            EnumWrappers.NativeGameMode.SURVIVAL,
                            null
                    ))
            );
    
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet, false);
        }
    
        // ADD PLAYER FOR EVERYONE
        {
            PacketContainer packet = new PacketContainer(PacketType.Play.Server.PLAYER_INFO);
            packet.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
            packet.getPlayerInfoDataLists().write(
                    0,
                    List.of(new PlayerInfoData(
                            new WrappedGameProfile(player.getUniqueId(), player.getName()),
                            player.getPing(),
                            EnumWrappers.NativeGameMode.fromBukkit(player.getGameMode()),
                            WrappedChatComponent.fromText(player.getName())
                    ))
            );
    
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet.deepClone());
            }
        }
    
        // RESPAWN FOR SELF
        {
            Bukkit.getServer().getWorlds().stream()
                    .filter(world -> world != player.getWorld())
                    .findFirst()
                    .ifPresent(world -> {
                        org.bukkit.Location location = player.getLocation();
                        player.teleport(world.getSpawnLocation());
                        player.teleport(location);
                    });
        }
    
        // Update visibility for all players
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.hidePlayer(SVUhc.getInstance(), player);
            p.showPlayer(SVUhc.getInstance(), player);
        }
    }

    public void unnick(Player player) {
        names.remove(player.getUniqueId());
        try {
            refresh(player);
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void nickForPlayer(Player target, String name, Player viewer) throws InvocationTargetException {
        
    
        // REMOVE TARGET FOR VIEWER
        {
            WrappedGameProfile previous = playerPreviousIdentities.remove(viewer);
            if (previous == null) {
                previous = new WrappedGameProfile(viewer.getUniqueId(), viewer.getName());
            }
    
            PacketContainer removePacket = new PacketContainer(PacketType.Play.Server.PLAYER_INFO);
            removePacket.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
            removePacket.getPlayerInfoDataLists().write(
                    0,
                    List.of(new PlayerInfoData(
                            previous,
                            0,
                            EnumWrappers.NativeGameMode.SURVIVAL,
                            null
                    ))
            );
    
            ProtocolLibrary.getProtocolManager().sendServerPacket(target, removePacket.deepClone(), false);
        }
    
        // ADD TARGET FOR VIEWER
        {
            PacketContainer addPacket = new PacketContainer(PacketType.Play.Server.PLAYER_INFO);
            addPacket.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
            addPacket.getPlayerInfoDataLists().write(
                    0,
                    List.of(new PlayerInfoData(
                            new WrappedGameProfile(target.getUniqueId(), name),
                            target.getPing(),
                            EnumWrappers.NativeGameMode.fromBukkit(target.getGameMode()),
                            WrappedChatComponent.fromText(name)
                    ))
            );
    
            ProtocolLibrary.getProtocolManager().sendServerPacket(viewer, addPacket.deepClone());
        }
    
        // Update visibility for target
        viewer.hidePlayer(SVUhc.getInstance(), target);
        viewer.showPlayer(SVUhc.getInstance(), target);
    }


    public PlayerProfile getEffectiveProfile(Player player) {
        String effectiveName = names.getOrDefault(player.getUniqueId(), player.getName());
        PlayerProfile profile = Bukkit.getServer().createProfile(effectiveName);
        profile.complete();
        if (profile.getId() == null) {
            return getOfflineProfile(effectiveName);
        }
        return profile;
    }

    private PlayerProfile getOfflineProfile(String name) {
        return Bukkit.getServer().createProfile(UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes()), name);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        try {
            refresh(event.getPlayer());
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
