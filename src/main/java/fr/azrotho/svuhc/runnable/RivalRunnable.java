package fr.azrotho.svuhc.runnable;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.azrotho.svuhc.SVUhc;
import fr.azrotho.svuhc.objects.SVPlayer;

public class RivalRunnable extends BukkitRunnable {

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            SVPlayer svPlayer = SVUhc.getInstance().players().getPlayer(player);
            if(SVUhc.getInstance().players().isMeilleurAmi(player) && SVUhc.getInstance().players().isGarcon(player)) {
                Player meilleureAmi = SVUhc.getInstance().players().getMeilleurAmiGarcon(player);
                if(meilleureAmi != null) {
                    if(meilleureAmi.getWorld().getName().equals(player.getWorld().getName())) {
                        if(meilleureAmi.getLocation().distance(player.getLocation()) <= 50) {
                            player.removePotionEffect(PotionEffectType.WEAKNESS);
                            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                        } else {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, -1, 0, false, false));
                            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(16);
                        }
                    }
                }

                Player rival = SVUhc.getInstance().players().getOtherPlayerInCouple(meilleureAmi);

                if(rival != null) {
                    if(rival.getWorld().getName().equals(player.getWorld().getName())) {
                        if(rival.getLocation().distance(player.getLocation()) <= 50) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, -1, 0, false, false));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 0, false, false));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, -1, 0, false, false));
                        } else {
                           player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                           player.removePotionEffect(PotionEffectType.SPEED);
                            player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                        }
                    }
                }
            }

        }
    }
    
}
