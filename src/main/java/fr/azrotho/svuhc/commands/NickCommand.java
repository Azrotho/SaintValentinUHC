package fr.azrotho.svuhc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import fr.azrotho.svuhc.SVUhc;

public class NickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String name,
            @NotNull String[] args) {
                if(commandSender instanceof Player player && player.isOp()) {
                if(args.length == 0) {
                    commandSender.sendMessage(SVUhc.getInstance().getTag() + "§cUsage: /nick <global|forPlayer> <player> <nickname> [target]");
                    return true;
                }

                if(args[0].equalsIgnoreCase("global")) {
                    if(args.length < 3) {
                        commandSender.sendMessage(SVUhc.getInstance().getTag() + "§cUsage: /nick global <player> <nickname>");
                        return true;
                    }
                    //SVUhc.getInstance().getNickManager().nick(player, args[2]);
                    return true;
                }

                if(args[0].equalsIgnoreCase("forPlayer")) {
                    if(args.length < 4) {
                        commandSender.sendMessage(SVUhc.getInstance().getTag() + "§cUsage: /nick forPlayer <player> <nickname> <target>");
                        return true;
                    }
                    Player target = SVUhc.getInstance().getServer().getPlayer(args[3]);
                    if(target == null) {
                        commandSender.sendMessage(SVUhc.getInstance().getTag() + "§cLe joueur " + args[3] + " n'est pas connecté.");
                        return true;
                    }
                    //SVUhc.getInstance().getNickManager().nickForPlayer(player, args[2], target);
                    return true;
                }

        return true;
        } else {
            commandSender.sendMessage(SVUhc.getInstance().getTag() + "§cVous n'avez pas la permission d'executer cette commande.");
        }
    return true;
    }
}
