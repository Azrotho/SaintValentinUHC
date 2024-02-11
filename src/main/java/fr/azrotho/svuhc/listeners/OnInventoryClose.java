package fr.azrotho.svuhc.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class OnInventoryClose implements Listener {
    
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        // Si l'inventaire fermé est celui de la demande de couple, alors on réouvre l'inventaire de l'autre joueur

        if(event.getView().getTitle().startsWith("§cDemande de couple de ")) {
            event.getPlayer().openInventory(event.getInventory());
        }

    }
    
}
