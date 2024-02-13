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

public class UWUCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String name,
            @NotNull String[] args) {
            if(commandSender instanceof Player player) {
                if(SVUhc.getInstance().players().isCouple(player)) {
                    SVCouple couple = SVUhc.getInstance().players().getCouple(player);
                    if(!SVUhc.getInstance().players().isInCooldown(couple, "uwu")) {
                        if(couple.getState().equals("kiff") || couple.getState().equals("crush")) {
                            player.sendMessage(SVUhc.getInstance().getTag() + "§fCette commande sera disponible quand tu aura trouvé ton bebou...désolé.");
                            return true;
                        } else {
                            Player player2 = SVUhc.getInstance().players().getCouple(player).getOtherPlayer(player);
                            player.sendMessage(SVUhc.getInstance().getTag() + "§fC'est si mignon, vous obtenez régénération 1 pendant 1 minute, faites-en bon usage.");
                            player2.sendMessage(SVUhc.getInstance().getTag() + "§fC'est si mignon, vous obtenez régénération 1 pendant 1 minute, faites-en bon usage.");

                            player2.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60 * 20, 0, false, false, false));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60 * 20, 0, false, false, false));

                            SVUhc.getInstance().players().addCooldown(couple, "uwu", 60 * 25);
                        }
                    } else {
                    commandSender.sendMessage(SVUhc.getInstance().getTag() + "§fElle ne semble pas vouloir de votre mignonnerie, réessaye dans §c" + SVUhc.getInstance().players().getCooldown(couple, "uwu") + "§f secondes.");
                    return true;
                    }
                return true;
                } else {
                    commandSender.sendMessage(SVUhc.getInstance().getTag() + "§fCette commande sera disponible quand tu aura trouvé ton bebou...désolé.");
                    return true;
            }
        }
    return true;
    }
}