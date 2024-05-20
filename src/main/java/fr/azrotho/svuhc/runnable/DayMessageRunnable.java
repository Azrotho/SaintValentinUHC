package fr.azrotho.svuhc.runnable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.azrotho.svuhc.SVUhc;

public class DayMessageRunnable extends BukkitRunnable {
    @Override
    public void run() {
        if (Bukkit.getWorld("world").getTime() == 0L) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(SVUhc.getInstance().getTag() + "§7Un nouveau jour se lève...");
            }
        }
    }
}
