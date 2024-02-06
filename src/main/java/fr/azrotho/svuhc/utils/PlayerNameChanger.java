package fr.azrotho.svuhc.utils;

// Importer les classes nécessaires
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;

// Définir la classe PlayerNameChanger qui hérite de JavaPlugin
public class PlayerNameChanger {

    // Déclarer un attribut pour le gestionnaire de protocole
    private ProtocolManager protocolManager;

    // Méthode qui s'exécute à l'initialisation de la classe
    public PlayerNameChanger() {
        // Récupérer le gestionnaire de protocole
        protocolManager = ProtocolLibrary.getProtocolManager();
    }


    // Méthode qui renomme un joueur
    public void renamePlayer(Player player, String newName) {
        // Créer un nouveau profil de jeu avec le nouveau nom
        WrappedGameProfile newProfile = new WrappedGameProfile(player.getUniqueId(), newName);

        // Créer un paquet pour mettre à jour le nom du joueur dans la liste des joueurs
        PacketContainer playerInfoPacket = protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO);
        // Définir les champs du paquet

        //TODO: Fix this
        playerInfoPacket.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.UPDATE_DISPLAY_NAME);
        playerInfoPacket.getPlayerInfoDataLists().write(0, Collections.singletonList(new PlayerInfoData(newProfile, 0, EnumWrappers.NativeGameMode.NOT_SET, null)));

        // Créer un paquet pour mettre à jour le nom du joueur au-dessus de sa tête
        PacketContainer entityDestroyPacket = protocolManager.createPacket(PacketType.Play.Server.ENTITY_DESTROY);
        // Définir les champs du paquet
        entityDestroyPacket.getIntegerArrays().write(0, new int[] {player.getEntityId()});

        PacketContainer namedEntitySpawnPacket = protocolManager.createPacket(PacketType.Play.Server.NAMED_ENTITY_SPAWN);
        // Définir les champs du paquet
        namedEntitySpawnPacket.getUUIDs().write(0, player.getUniqueId());
        namedEntitySpawnPacket.getIntegers().write(0, player.getEntityId());
        namedEntitySpawnPacket.getDoubles().write(0, player.getLocation().getX());
        namedEntitySpawnPacket.getDoubles().write(1, player.getLocation().getY());
        namedEntitySpawnPacket.getDoubles().write(2, player.getLocation().getZ());
        namedEntitySpawnPacket.getBytes().write(0, (byte) (player.getLocation().getYaw() * 256 / 360));
        namedEntitySpawnPacket.getBytes().write(1, (byte) (player.getLocation().getPitch() * 256 / 360));
        namedEntitySpawnPacket.getDataWatcherModifier().write(0, WrappedDataWatcher.getEntityWatcher(player));

        // Envoyer les paquets à tous les joueurs en ligne
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            try {
                protocolManager.sendServerPacket(online, playerInfoPacket);
                protocolManager.sendServerPacket(online, entityDestroyPacket);
                protocolManager.sendServerPacket(online, namedEntitySpawnPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
