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

public class ColleSerreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String name, @NotNull String[] args) {
        if(commandSender instanceof Player player) {
            if(SVUhc.getInstance().players().isCouple(player)) {
                SVCouple couple = SVUhc.getInstance().players().getCouple(player);
                if(!SVUhc.getInstance().players().isInCooldown(couple, "colleserre")) {

                    if(!couple.getState().equals("marie")) {
                        player.sendMessage(SVUhc.getInstance().getTag() + "§fVous devez avoir un labrador, deux gosses, une femme et un scénic rouge pour faire du caliente caliente.");
                        return true;
                    }

                    Player player2 = SVUhc.getInstance().players().getCouple(player).getOtherPlayer(player);
                    player.sendMessage(SVUhc.getInstance().getTag() + "§fGrrrr, ça zook sous la couette, vous vous êtes envoyé en l'air, vous avez donc l'effet jump boost 4 et speed 2 pendant 2 minutes.");
                    player2.sendMessage(SVUhc.getInstance().getTag() + "§fGrrrr, ça zook sous la couette, vous vous êtes envoyé en l'air, vous avez donc l'effet jump boost 4 et speed 2 pendant 2 minutes.");

                    player2.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60 * 20 * 2, 3, false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60 * 20 * 2, 3, false, false, false));

                    player2.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60 * 20 * 2, 1, false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60 * 20 * 2, 1, false, false, false));

                    SVUhc.getInstance().players().addCooldown(couple, "colleserre", 60 * 30);
                    return true;

                } else {
                    player.sendMessage(SVUhc.getInstance().getTag() + "§fElle ne semble pas avoir envie de vous embrasser, réessaye dans §c" + String.valueOf(SVUhc.getInstance().players().getCooldown(couple, "bisou")) + "s");
                }
                

            } else {
                player.sendMessage(SVUhc.getInstance().getTag() + "§cVous devez avoir un labrador, deux gosses, une femme et un scénic rouge pour faire du caliente caliente.");
            }
        }
        
        return true;
    }
    
}
