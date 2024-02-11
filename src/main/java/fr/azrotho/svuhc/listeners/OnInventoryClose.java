package fr.azrotho.svuhc.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import fr.azrotho.svuhc.SVUhc;
import fr.azrotho.svuhc.utils.CoupleMenu;

public class OnInventoryClose implements Listener {

    public static List<Player> letPlayer = new ArrayList<Player>();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        // Si l'inventaire fermé est celui de la demande de couple, alors on réouvre l'inventaire de l'autre joueur

        if(event.getView() == null) return;
        if(event.getView().getTitle() == null) return;
        if(event.getView().getTitle().isEmpty()) return;

        if(event.getView().getTitle().startsWith("§cDemande de couple de ")) {
            Player player = (Player) event.getPlayer();
            Player target = player.getServer().getPlayer(event.getView().getTitle().replace("§cDemande de couple de ", ""));
            if(target == null) return;
            Bukkit.getScheduler().runTaskLater(SVUhc.getInstance(), () -> {
                if(letPlayer.contains(event.getPlayer())) {
                    letPlayer.remove(event.getPlayer());
                    return;
                }
                new CoupleMenu().couple(player, target);
            }, 5);
        }

    }
    
}
