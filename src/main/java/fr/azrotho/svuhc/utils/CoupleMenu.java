package fr.azrotho.svuhc.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CoupleMenu {

    @SuppressWarnings("deprecation")
    public void couple(Player player, Player target) {
        // Ouvrir un inventaire avec 2 items, un pour devenir le pour accepter la demande de target et un pour refuser
        // Si le joueur clique sur l'item pour accepter, alors on appelle la méthode setMeilleurAmi de SVPlayers
        // Si le joueur clique sur l'item pour refuser, alors on envoie un message à target pour lui dire que la demande a été refusée

        Inventory inv = Bukkit.createInventory(null, 9, "§cDemande de couple de " + target.getName());
        player.openInventory(inv);

        // Item pour accepter
        ItemStack accept = new ItemStack(Material.LIME_WOOL, 1);
        ItemMeta acceptMeta = accept.getItemMeta();
        acceptMeta.setDisplayName("§aAccepter");
        accept.setItemMeta(acceptMeta);
        inv.setItem(3, accept);

        // Item pour refuser
        ItemStack refuse = new ItemStack(Material.RED_WOOL, 1);
        ItemMeta refuseMeta = refuse.getItemMeta();
        refuseMeta.setDisplayName("§cRefuser");
        refuse.setItemMeta(refuseMeta);
        inv.setItem(5, refuse);

    }
}
