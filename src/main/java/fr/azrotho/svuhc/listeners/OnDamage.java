package fr.azrotho.svuhc.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.azrotho.svuhc.SVUhc;

public class OnDamage implements Listener {
    @EventHandler
    public void on(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player player) {
            if(!SVUhc.getInstance().isStarted()) {
                event.setCancelled(true);
                return;
            }

            if(SVUhc.getInstance().players().isCoupleMarie(player)) {
                Player partner = SVUhc.getInstance().players().getOtherPlayerInCouple(player);
                if(partner == null) return;
                partner.damage(event.getFinalDamage());
                return;
            }
        }
    }
    
}
