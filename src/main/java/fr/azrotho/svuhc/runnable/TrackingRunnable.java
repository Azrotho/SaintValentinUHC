package fr.azrotho.svuhc.runnable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.azrotho.svuhc.SVUhc;
import fr.azrotho.svuhc.utils.PluginsItemStackUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class TrackingRunnable extends BukkitRunnable {

    @SuppressWarnings("deprecation")
    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getInventory().getItemInMainHand() == null) continue;
            if(player.getInventory().getItemInMainHand().getItemMeta() == null) continue;
            if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName() == null) continue;
            if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§e§lLocalisation Snap")) {
                if(SVUhc.getInstance().players().isMeilleurAmi(player) && SVUhc.getInstance().players().isGarcon(player)) {
                    Player target = SVUhc.getInstance().players().getCrushDeLaMeilleurePote(player);
                    if(target == null) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§c§lX"));
                        return;
                    }

                    if(!target.getWorld().getName().equals(player.getWorld().getName())) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§c§lX Dimension"));
                        return;
                    }

                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§a§l" + player.getLocation().distance(target.getLocation()) + " m"));
                    player.setCompassTarget(target.getLocation());
                }
            }
        }
    }
    
}
