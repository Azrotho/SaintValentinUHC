package fr.azrotho.svuhc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import fr.azrotho.svuhc.SVUhc;

public class TeamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String commandName, @NotNull String[] args) {
        if(!commandSender.isOp()) { commandSender.sendMessage(SVUhc.getInstance().getTag() + "§cVous n'avez pas la permission d'executer cette commande."); return true; }
        switch(args[0]) {
            case "setTeam":
                // Possibilité de set la team d'un joueur uniquement sur garcon/fille
                if(args.length < 3) {
                    commandSender.sendMessage("§cErreur: /team setTeam <player> <team>");
                    return true;
                }
                break;
            case "getPlayerTeams":
                break;    
        }
        return true;
    }
    
}
