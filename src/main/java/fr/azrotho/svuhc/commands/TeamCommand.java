package fr.azrotho.svuhc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TeamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String commandName, @NotNull String[] args) {
        switch(args[0]) {
            case "setTeam":
                break;
            case "getPlayerTeams":
                break;    
        }
        return true;
    }
    
}
