package fr.azrotho.svuhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.azrotho.svuhc.SVUhc;

public class OnInventoryInteraction implements Listener {
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInventoryInteraction(InventoryClickEvent event) {
        if(event.getView().getTitle().startsWith("§cDemande de couple de ")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            Player target = Bukkit.getPlayer(event.getView().getTitle().replace("§cDemande de couple de ", ""));

            if(event.getCurrentItem() == null) return;
            if(event.getCurrentItem().getItemMeta() == null) return;
            if(event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§aAccepter")) {
                // Couple

                player.sendMessage(SVUhc.getInstance().getTag() + "§aVous avez accepté la demande de couple de " + target.getName());
                target.sendMessage(SVUhc.getInstance().getTag() + "§a" + player.getName() + " a accepté votre demande de couple");

                player.closeInventory();
                
                SVUhc.getInstance().players().addRelation(player, target, "kiff");
                SVUhc.getInstance().players().addRelation(target, player, "kiff");
                OnInventoryClose.letPlayer.add(player);
            } 
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cRefuser")) {
                // Refuser
                
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 2);
                player.sendMessage(SVUhc.getInstance().getTag() + "§cVous avez refusé la demande de couple de " + target.getName());

                target.sendMessage(SVUhc.getInstance().getTag() + "§c" + player.getName() + " a refusé votre demande de couple");
                target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - 2);

                OnInventoryClose.letPlayer.add(player);
                player.closeInventory();
            }
        }
    }
        
}
