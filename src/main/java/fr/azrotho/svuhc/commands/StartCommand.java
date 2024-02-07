package fr.azrotho.svuhc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import fr.azrotho.svuhc.SVUhc;

public class StartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender arg0, @NotNull Command arg1, @NotNull String arg2, @NotNull String[] arg3) {
        SVUhc.getInstance().setStarted(true);
        return true;
    }
    
}