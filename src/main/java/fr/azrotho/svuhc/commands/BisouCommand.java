package fr.azrotho.svuhc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import fr.azrotho.svuhc.SVUhc;
import fr.azrotho.svuhc.objects.SVCouple;

public class BisouCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String name, @NotNull String[] args) {
        if(commandSender instanceof Player player) {
            if(SVUhc.getInstance().players().isCouple(player)) {
                SVCouple couple = SVUhc.getInstance().players().getCouple(player);
                if(!SVUhc.getInstance().players().isInCooldown(couple, "bisou")) {
                    Player player2 = SVUhc.getInstance().players().getCouple(player).getOtherPlayer(player);
                    player.sendMessage(SVUhc.getInstance().getTag() + "§fVous avez eu un p'tit bisou, ça vous donne de la force, vous avez donc l'effet force 1 pendant 30 secondes, faites-en bon usage.");
                    player2.sendMessage(SVUhc.getInstance().getTag() + "§fVous avez eu un p'tit bisou, ça vous donne de la force, vous avez donc l'effet force 1 pendant 30 secondes, faites-en bon usage.");

                    player2.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 30 * 20, 0, false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 30 * 20, 0, false, false, false));

                    SVUhc.getInstance().players().addCooldown(couple, "bisou", 60 * 20);
                    return true;
                } else {
                    player.sendMessage(SVUhc.getInstance().getTag() + "§fElle ne semble pas avoir envie de vous embrasser, réessaye dans §c" + String.valueOf(SVUhc.getInstance().players().getCooldown(couple, "bisou")) + "s");
                }
                

            } else {
                player.sendMessage(SVUhc.getInstance().getTag() + "§cCette commande est réservé aux couples...désolé. !");
            }
        }
        
        return true;
    }
    
}
