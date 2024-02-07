package fr.azrotho.svuhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.azrotho.svuhc.SVUhc;

public class OnJoin implements Listener {
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        SVUhc.getInstance().players().addPlayer(event.getPlayer());
        if(!event.getPlayer().hasPlayedBefore()) event.getPlayer().sendMessage(SVUhc.getInstance().getTag() + "§cBienvenue sur le Serveur !");
        Player player = event.getPlayer();
        if(!SVUhc.getInstance().isStarted()) {
            player.teleport(new org.bukkit.Location(Bukkit.getWorld("world"), 0, 196, 0));
            // Create box at 0,0
            for (int x = -5; x < 5; x++) {
                for (int y = 195; y < 200; y++) {
                    for (int z = -5; z < 5; z++) {
                        player.getWorld().getBlockAt(x, y, z).setType(Material.BARRIER);
                    }
                }
            }
            for (int x = -4; x < 4; x++) {
                for (int y = 196; y < 199; y++) {
                    for (int z = -4; z < 4; z++) {
                        player.getWorld().getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }
            }
        }
    }
}
