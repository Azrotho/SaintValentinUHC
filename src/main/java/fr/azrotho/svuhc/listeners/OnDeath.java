package fr.azrotho.svuhc.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.azrotho.svuhc.SVUhc;

public class OnDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.getPlayer().setGameMode(GameMode.SPECTATOR);

        if(SVUhc.getInstance().players().isCoupleMarie(event.getPlayer())) {
            Player partner = SVUhc.getInstance().players().getOtherPlayerInCouple(event.getPlayer());
            if(partner == null) return;
            SVUhc.getInstance().players().removePlayerRelations(event.getPlayer());
            partner.damage(1000.0D);
            return;
        }

        SVUhc.getInstance().players().removePlayerRelations(event.getPlayer());
    }
}
