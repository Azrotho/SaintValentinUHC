package fr.azrotho.svuhc.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Timber implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.getBlock().getType().name().contains("LOG")) return;

        if(event.getBlock().getType().name().contains("LOG")) {
            List<Location> logs = new ArrayList<>();
            logs.add(event.getBlock().getLocation());
            int i = 0;
            while(i < logs.size()) {
                Location loc = logs.get(i);
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        for (int z = -1; z <= 1; z++) {
                            Location newLoc = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ() + z);
                            if (newLoc.getBlock().getType().name().contains("LOG") && !logs.contains(newLoc)) {
                                logs.add(newLoc);
                            }
                        }
                    }
                }
                i++;
            }
            for (Location loc : logs) {
                loc.getBlock().breakNaturally(event.getPlayer().getInventory().getItemInMainHand());
                ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
                item.damage(1, event.getPlayer());
            }
        }
    }
}
