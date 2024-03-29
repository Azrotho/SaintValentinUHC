package fr.azrotho.svuhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;

import fr.azrotho.svuhc.SVUhc;


public class FastSmelting implements Listener {
    
    private final int speed = 50;

    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent e){
        Block block = e.getBlock();

        Bukkit.getScheduler().runTaskLater(SVUhc.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (block.getType() == Material.AIR){
                    return;
                }

                Furnace furnace = (Furnace) block.getState();

                if (furnace.getBurnTime() <= 50) {
                    return;
                }

                if (furnace.getCookTime() <= 0){
                    Bukkit.getScheduler().runTaskLater(SVUhc.getInstance(), this, 5);
                    return;
                }

                short newCookTime = (short) (furnace.getCookTime() + speed);

                if (newCookTime >= 200){
                    newCookTime = 199;
                }

                furnace.setCookTime(newCookTime);
                furnace.update();
                Bukkit.getScheduler().runTaskLater(SVUhc.getInstance(), this, 2);
            }
        }, 1);
    }
}
