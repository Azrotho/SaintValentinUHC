package fr.azrotho.svuhc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import fr.azrotho.svuhc.SVUhc;
import fr.azrotho.svuhc.utils.CoupleMenu;

public class CoupleCommand implements CommandExecutor{

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String name, @NotNull String[] args) {
        // Usage: /couple <player>, nécessite que le commandSender et player soient à moins de 50 blocs l'un de l'autre dans la même dimension

        if(args.length != 1) {
            commandSender.sendMessage(SVUhc.getInstance().getTag() + "§cUsage: /couple <player>");
            return true;
        }

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cVous devez être un joueur pour executer cette commande.");
            return true;
        }

        Player p = (Player) commandSender;
        Player target = Bukkit.getPlayer(args[0]);

        
        if(target == null) {
            p.sendMessage(SVUhc.getInstance().getTag() + "§cLe joueur §4" + args[0] + "§c n'est pas connecté/n'existe pas.");
            return true;
        }

        if(p == target) {
            p.sendMessage(SVUhc.getInstance().getTag() + "§cVous ne pouvez pas vous mettre en couple avec vous même.");
            return true;
        }

        if(p.getWorld() != target.getWorld()) {
            p.sendMessage(SVUhc.getInstance().getTag() + "§cLe joueur §4" + args[0] + "§c n'est pas dans la même dimension que vous.");
            return true;
        }

        if(p.getLocation().distance(target.getLocation()) > 20) {
            p.sendMessage(SVUhc.getInstance().getTag() + "§cLe joueur §4" + args[0] + "§c est trop loin de vous.");
            return true;
        }

        if(SVUhc.getInstance().players().isCouple(p)) {
            p.sendMessage(SVUhc.getInstance().getTag() + "§cVous êtes déjà en couple.");
            return true;
        }

        if(SVUhc.getInstance().players().isMeilleurAmi(p) && SVUhc.getInstance().players().isGarcon(p)) {
            // Faire en sorte que le joueur p ne peut que demander à sa meilleur amie de devenir son couple
            if(!SVUhc.getInstance().players().isMeilleurAmi(target)) {
                p.sendMessage(SVUhc.getInstance().getTag() + "§cVous ne pouvez demander qu'à votre meilleure amie de devenir votre couple.");
                return true;
            }
        }

        new CoupleMenu().couple(target, p);

        return true;
    }
    
}
