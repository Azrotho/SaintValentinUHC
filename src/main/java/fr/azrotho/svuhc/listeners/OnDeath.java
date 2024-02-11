package fr.azrotho.svuhc.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.azrotho.svuhc.SVUhc;

public class OnDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.getPlayer().setGameMode(GameMode.SPECTATOR);
        // Définir toute les relations du joueur comme None
        // Supprimer le joueur de toute les relations

        SVUhc.getInstance().players().removePlayerRelations(event.getPlayer());
    }
}
