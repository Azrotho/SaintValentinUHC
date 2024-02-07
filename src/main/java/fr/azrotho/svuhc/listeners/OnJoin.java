package fr.azrotho.svuhc.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.azrotho.svuhc.SVUhc;

public class OnJoin implements Listener {
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        SVUhc.getInstance().players().addPlayer(event.getPlayer());
        if(event.getPlayer().hasPlayedBefore()) return;
        event.getPlayer().sendMessage(SVUhc.getInstance().getTag() + "§cBienvenue sur le Serveur !");
    }
}
