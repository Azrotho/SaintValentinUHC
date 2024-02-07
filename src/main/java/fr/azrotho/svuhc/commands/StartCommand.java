package fr.azrotho.svuhc.commands;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.jetbrains.annotations.NotNull;

import fr.azrotho.svuhc.SVUhc;

public class StartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String name, @NotNull String[] args) {
        if(!commandSender.isOp()) {
            commandSender.sendMessage(SVUhc.getInstance().getTag() + "§cVous n'avez pas la permission d'executer cette commande.");
            return true;
        }
        SVUhc.getInstance().setStarted(true);
        SVUhc.getInstance().players().designeMeilleurAmi();

        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            
            p.getInventory().clear();
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10 * 20 * 60, 20, true));

            // Téléportation Aléatoire

            Location location = new Location(p.getWorld(), 0, 0, 0);

            location.setX(Math.random() * 500 * 2 - 500);
            location.setZ(Math.random() * 500 * 2 - 500);
            location.setY(p.getWorld().getHighestBlockAt(location.getBlockX(), location.getBlockZ()).getY());

            p.teleport(location);
            ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 8);
            p.getInventory().addItem(gapple);
            p.setGameMode(GameMode.SURVIVAL);
        }

        for(World w : Bukkit.getWorlds()){
            w.setGameRule(GameRule.NATURAL_REGENERATION, false);
            w.setDifficulty(Difficulty.EASY);
            w.setTime(0);
        }      
        return true;  
    }

}
