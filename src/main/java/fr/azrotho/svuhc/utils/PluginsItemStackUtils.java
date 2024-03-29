package fr.azrotho.svuhc.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class PluginsItemStackUtils {

    @SuppressWarnings("deprecation")
    public static ItemStack getTracker() {
        ItemStack tracker = new ItemStack(Material.COMPASS);
        ItemMeta trackerMeta = tracker.getItemMeta();
        trackerMeta.setDisplayName("§e§lLocalisation Snap");
        trackerMeta.setLore(List.of(
            "§eTenez cette boussole dans la main pour tracker",
            "§ele crush de votre pote."
        ));
        tracker.setItemMeta(trackerMeta);
        return tracker;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack getProtectionBook() {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) book.getItemMeta();
        bookMeta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        bookMeta.setDisplayName("§e§lProtection IV");
        book.setItemMeta(bookMeta);
        return book;
    }
    
}
