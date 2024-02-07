package fr.azrotho.svuhc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import fr.azrotho.svuhc.SVUhc;
import fr.azrotho.svuhc.objects.SVPlayer;

public class TeamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String commandName, @NotNull String[] args) {
        if(!commandSender.isOp()) { commandSender.sendMessage(SVUhc.getInstance().getTag() + "§cVous n'avez pas la permission d'executer cette commande."); return true; }
        switch(args[0]) {
            case "setTeam":
                // Possibilité de set la team d'un joueur uniquement sur garcon/fille
                if(args.length < 3) {
                    commandSender.sendMessage(SVUhc.getInstance().getTag() + "§cErreur: /team setTeam <player> <team>");
                    return true;
                }
                if(args[2].equalsIgnoreCase("garcon") || args[2].equalsIgnoreCase("fille")) {
                    commandSender.sendMessage(SVUhc.getInstance().getTag() + "§cErreur: La team doit être soit garcon soit fille.");
                    return true;
                }

                Player player = Bukkit.getPlayer(args[1]);
                if(player == null) {
                    commandSender.sendMessage(SVUhc.getInstance().getTag() + "§cErreur: Le joueur n'existe pas.");
                    return true;
                }
                SVPlayer sVPlayer = SVUhc.getInstance().players().getPlayer(player);
                sVPlayer.setTeam(args[2].toLowerCase());
                break;
            case "getPlayerTeams":
                // Possibilité de voir la team de tous les joueurs
                for(SVPlayer svPlayer : SVUhc.getInstance().players().players) {
                    Player player1 = Bukkit.getPlayer(svPlayer.getUuid());
                    commandSender.sendMessage(player1.getName() + " : " + svPlayer.getTeam());
                }
                break;    
        }
        return true;
    }
    
}
