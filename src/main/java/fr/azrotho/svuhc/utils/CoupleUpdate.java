package fr.azrotho.svuhc.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import fr.azrotho.svuhc.SVUhc;
import fr.azrotho.svuhc.objects.SVCouple;

public class CoupleUpdate {
    public static void update(SVCouple couple) {
        Player player1 = Bukkit.getPlayer(couple.getPlayer1().getUuid());
        Player player2 = Bukkit.getPlayer(couple.getPlayer2().getUuid());

        if(couple.getTime() == 600 && couple.getState().equals("kiff")) {
            couple.setState("crush");
            player1.sendMessage(SVUhc.getInstance().getTag() + "§fOn dirait bien que le stade du simple kiff est passé, vous êtes désormais en crush avec avec §d§l"+ player2.getName() + "§f, vous gagnez ainsi deux coeurs d'abso non définitifs !");
            player2.sendMessage(SVUhc.getInstance().getTag() + "§fOn dirait bien que le stade du simple kiff est passé, vous êtes désormais en crush avec avec §d§l"+ player1.getName() + "§f, vous gagnez ainsi deux coeurs d'abso non définitifs !");

            player1.addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.ABSORPTION, 60 * 20 * 10, 0, false, false));
            player2.addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.ABSORPTION, 60 * 20 * 10, 0, false, false));

            couple.setTime(0);
        }

        if(couple.getTime() == 600 && couple.getState().equals("crush")) {
            couple.setState("bebou");
            player1.sendMessage(SVUhc.getInstance().getTag() + "§fOn dirait bien que le stade du crush est passé, §d§l" + player2.getName() + " §fest désormais votre bebou, à force de vous envoyer des coeurs, vous avez gagné le sien, c'est pourquoi vous obtenez un coeur supplémentaire définitif et la possibilité de faire le commande /uwu.");
            player2.sendMessage(SVUhc.getInstance().getTag() + "§fOn dirait bien que le stade du crush est passé, §d§l" + player1.getName() + " §fest désormais votre bebou, à force de vous envoyer des coeurs, vous avez gagné le sien, c'est pourquoi vous obtenez un coeur supplémentaire définitif et la possibilité de faire le commande /uwu.");

            player1.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player1.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 2);
            player2.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player2.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 2);

            couple.setTime(0);

            if(SVUhc.getInstance().players().isLaMeilleureAmiFille(player1)) {
                Player MeilleurAmi = SVUhc.getInstance().players().getMeilleurAmiGarcon(player1);
                MeilleurAmi.sendMessage(SVUhc.getInstance().getTag() + "§fOh SAPERLIPOFOUETTE, ta meilleure pote est le bebou d'un mec, va vraiment falloir s'en débarasser, tu décide d'activer sa localisation snap pour agir et récupérer ta POTE, tu reçois donc une boussole qui pointe vers le mec de ta POTE.");
                MeilleurAmi.getInventory().addItem(PluginsItemStackUtils.getTracker());
            }

            if(SVUhc.getInstance().players().isLaMeilleureAmiFille(player2)) {
                Player MeilleurAmi = SVUhc.getInstance().players().getMeilleurAmiGarcon(player2);
                MeilleurAmi.sendMessage(SVUhc.getInstance().getTag() + "§fOh SAPERLIPOFOUETTE, ta meilleure pote est le bebou d'un mec, va vraiment falloir s'en débarasser, tu décide d'activer sa localisation snap pour agir et récupérer ta POTE, tu reçois donc une boussole qui pointe vers le mec de ta POTE.");
                MeilleurAmi.getInventory().addItem(PluginsItemStackUtils.getTracker());
            }
        }
        
        if(couple.getTime() == 600 && couple.getState().equals("bebou")) {
            couple.setState("canard");

            player1.sendMessage(SVUhc.getInstance().getTag() + "§fOn dirait bien que la relation avance, malheureusement, vous êtes devenu un véritable canard... Vous écopez donc de l'effet weakness 1 jusqu'à vos fillancaïlles.");
            player2.sendMessage(SVUhc.getInstance().getTag() + "§fOn dirait bien que la relation avance, malheureusement, vous êtes devenu un véritable canard... Vous écopez donc de l'effet weakness 1 jusqu'à vos fillancaïlles.");

            player1.addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.WEAKNESS, 60 * 20 * 10, 0, false, false));
            player2.addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.WEAKNESS, 60 * 20 * 10, 0, false, false));

            couple.setTime(0);
        }

        if(couple.getTime() == 600 && couple.getState().equals("canard")) {
            couple.setState("fillance");
            player1.sendMessage(SVUhc.getInstance().getTag() + "§fOn dirait bien que l'heure est à la demande...Vous venez de vous passer la bague au doigts, vous êtes fillancés avec §d§l" + player2.getName() + "§f, vous gagnez donc 2 coeurs définitifs supplémentaires et perdez votre effet weakness 1");
            player2.sendMessage(SVUhc.getInstance().getTag() + "§fOn dirait bien que l'heure est à la demande...Vous venez de vous passer la bague au doigts, vous êtes fillancés avec §d§l" + player1.getName() + "§f, vous gagnez donc 2 coeurs définitifs supplémentaires et perdez votre effet weakness 1");

            player1.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player1.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 4);
            player2.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player2.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 4);

            couple.setTime(0);
        }

        if(couple.getTime() == 600 && couple.getState().equals("fillance")) {
            couple.setState("marie");
            player1.sendMessage(SVUhc.getInstance().getTag() + "§fC'est le grand jour, vous êtes MARIÉS, 2 GOSSES, 1 LABRADOR ET UN SCÉNIC ROUGE avec §d§l"+ player2.getName() + "§f, Comme ont dit, après le mariage on partage tout, le meilleur comme le pire...Alors il est temps de partager votre vie, vous avez désormais 30 coeurs, mais votre vie est commune. Votre entourage s'est cotisé pour faire des cadeaux aux mariés, voici un livre protection IV et 5 pommes d'or qui devraient vous aider. Vous avez aussi désormais accès à la commande /colléserre.");
            player2.sendMessage(SVUhc.getInstance().getTag() + "§fC'est le grand jour, vous êtes MARIÉS, 2 GOSSES, 1 LABRADOR ET UN SCÉNIC ROUGE avec §d§l"+ player1.getName() + "§f, Comme ont dit, après le mariage on partage tout, le meilleur comme le pire...Alors il est temps de partager votre vie, vous avez désormais 30 coeurs, mais votre vie est commune. Votre entourage s'est cotisé pour faire des cadeaux aux mariés, voici un livre protection IV et 5 pommes d'or qui devraient vous aider. Vous avez aussi désormais accès à la commande /colléserre.");

            player1.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            player2.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);

            player1.getInventory().addItem(PluginsItemStackUtils.getProtectionBook());
            player2.getInventory().addItem(PluginsItemStackUtils.getProtectionBook());

            player1.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 5));
            player2.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 5));

            couple.setTime(0);

            if(SVUhc.getInstance().players().isLaMeilleureAmiFille(player1)) {
                Player MeilleurAmi = SVUhc.getInstance().players().getMeilleurAmiGarcon(player1);
                player1.sendMessage(SVUhc.getInstance().getTag() + "§fTon meilleur ami s'est suicidé, il à laissé une lettre dans laquelle il conféssais ''C'était la femme de ma vie, aujourd'hui elle s'est mariée, si je ne peux plus l'avoir à quoi bon être son ami...C'est finis, je t'aimais...''");
                MeilleurAmi.sendMessage(SVUhc.getInstance().getTag() + "§fVotre meilleure amie est mariée, vous ne pourrez jamais la récupérer, vous décider donc d'en finir.");
                MeilleurAmi.damage(1000.0D);

            }

            if(SVUhc.getInstance().players().isLaMeilleureAmiFille(player2)) {
                Player MeilleurAmi = SVUhc.getInstance().players().getMeilleurAmiGarcon(player2);
                player2.sendMessage(SVUhc.getInstance().getTag() + "§fTon meilleur ami s'est suicidé, il à laissé une lettre dans laquelle il conféssais ''C'était la femme de ma vie, aujourd'hui elle s'est mariée, si je ne peux plus l'avoir à quoi bon être son ami...C'est finis, je t'aimais...''");
                MeilleurAmi.sendMessage(SVUhc.getInstance().getTag() + "§fVotre meilleure amie est mariée, vous ne pourrez jamais la récupérer, vous décider donc d'en finir.");
                MeilleurAmi.damage(1000.0D);
            }

        }
    }
}