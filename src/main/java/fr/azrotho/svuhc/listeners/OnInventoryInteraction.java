package fr.azrotho.svuhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.azrotho.svuhc.SVUhc;

public class OnInventoryInteraction implements Listener {
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInventoryInteraction(InventoryClickEvent event) {
        if(event.getView().getTitle().startsWith("§cDemande de couple de ")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            Player target = Bukkit.getPlayer(event.getView().getTitle().replace("§cDemande de couple de ", ""));

            if(event.getCurrentItem() == null) return;
            if(event.getCurrentItem().getItemMeta() == null) return;
            if(event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§aAccepter")) {
                // Couple

                player.sendMessage(SVUhc.getInstance().getTag() + "§aFélicitations, on dirait bien que votre date s'est bien déroulé, vous êtes en kiff mutuel avec §d§l" + target.getName());
                target.sendMessage(SVUhc.getInstance().getTag() + "§aFélicitations, on dirait bien que votre date s'est bien déroulé, vous êtes en kiff mutuel avec §d§l" + player.getName());

                player.closeInventory();
                
                SVUhc.getInstance().players().addRelation(player, target, "kiff");
                SVUhc.getInstance().players().addRelation(target, player, "kiff");
                OnInventoryClose.letPlayer.add(player);
            } 
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cRefuser")) {
                // Refuser

                if(SVUhc.getInstance().players().isMeilleurAmi(target) && SVUhc.getInstance().players().isGarcon(target)) {
                    player.sendMessage(SVUhc.getInstance().getTag() + "§cTu à friendzone ton meilleur ami, il s'est alors suicidé, il à laissé une lettre dans laquelle il conféssais ");
                    target.sendMessage(SVUhc.getInstance().getTag() + "§cVotre meilleure amie vous à mis un rateau, vous ne pourrez jamais la récupérer, vous décider donc d'en finir.");
                    target.damage(9999);
                    target.setGameMode(GameMode.SPECTATOR);
                    return;

                }
                
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 2);
                player.sendMessage(SVUhc.getInstance().getTag() + "§7Vous avez étés dure mais peux-être avait-il lâcher une caisse durant le diner... vous avez mis un rateau à §a" + target.getName() + "§7, vous gagnez un coeur définitif mais ne pourrez plus vous remettre avec lui");

                target.sendMessage(SVUhc.getInstance().getTag() + "§7La vie est parfois parcemée d'embuche, en effet l'heure est au chagrin d'amour puisque §d§l" + player.getName() +  "§7 vous à mis un râteau, vous perdez alors un coeur définitif et ne pourrez plus jamais vous mettre en couple avec elle.");
                target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - 2);

                SVUhc.getInstance().players().addRateau(player, target);
                OnInventoryClose.letPlayer.add(player);
                player.closeInventory();
            }
        }
    }
        
}
