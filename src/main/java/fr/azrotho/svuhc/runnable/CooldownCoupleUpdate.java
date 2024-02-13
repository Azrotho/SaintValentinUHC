package fr.azrotho.svuhc.runnable;

import java.util.Iterator;

import org.bukkit.scheduler.BukkitRunnable;

import fr.azrotho.svuhc.SVUhc;
import fr.azrotho.svuhc.objects.SVCouple;

public class CooldownCoupleUpdate extends BukkitRunnable {

    @Override
    public void run() {
        for(SVCouple couple : SVUhc.getInstance().players().couples) {
            SVUhc.getInstance().players().updateCooldown(couple);
        }
    }
    
}
