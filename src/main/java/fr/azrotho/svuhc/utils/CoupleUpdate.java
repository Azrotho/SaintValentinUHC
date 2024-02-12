package fr.azrotho.svuhc.utils;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import fr.azrotho.svuhc.SVUhc;
import fr.azrotho.svuhc.objects.SVCouple;

public class CoupleUpdate {
    public static void update(SVCouple couple) {
        Player player1 = Bukkit.getPlayer(couple.getPlayer1().getUuid());
        Player player2 = Bukkit.getPlayer(couple.getPlayer2().getUuid());

        if(couple.getTime() == 600 && couple.getState().equals("kiff")) {
            couple.setState("crush");
            player1.sendMessage(SVUhc.getInstance().getTag() + "§7On dirait bien que le stade du simple kiff est passé, vous êtes désormais en crush avec avec §d§l"+ player2.getName() + "§7, vous gagnez ainsi deux coeurs d'abso non définitifs !");
            player2.sendMessage(SVUhc.getInstance().getTag() + "§7On dirait bien que le stade du simple kiff est passé, vous êtes désormais en crush avec avec §d§l"+ player1.getName() + "§7, vous gagnez ainsi deux coeurs d'abso non définitifs !");

            player1.addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.ABSORPTION, 60 * 20 * 10, 0, false, false));
            player2.addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.ABSORPTION, 60 * 20 * 10, 0, false, false));

            couple.setTime(0);
        }

        if(couple.getTime() == 600 && couple.getState().equals("crush")) {
            couple.setState("bebou");
            player1.sendMessage(SVUhc.getInstance().getTag() + "§7On dirait bien que le stade du crush est passé, §d§l" + player2.getName() + " §7est désormais votre bebou, à force de vous envoyer des coeurs, vous avez gagné le sien, c'est pourquoi vous obtenez un coeur supplémentaire définitif et la possibilité de faire le commande /uwu.");
            player2.sendMessage(SVUhc.getInstance().getTag() + "§7On dirait bien que le stade du crush est passé, §d§l" + player1.getName() + " §7est désormais votre bebou, à force de vous envoyer des coeurs, vous avez gagné le sien, c'est pourquoi vous obtenez un coeur supplémentaire définitif et la possibilité de faire le commande /uwu.");

            player1.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player1.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 2);
            player2.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player2.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 2);

            couple.setTime(0);

            if(SVUhc.getInstance().players().isLaMeilleureAmiFille(player1)) {
                Player MeilleurAmi = SVUhc.getInstance().players().getMeilleurAmiGarcon(player1);
                MeilleurAmi.sendMessage(SVUhc.getInstance().getTag() + "§7Oh SAPERLIPOFOUETTE, ta meilleure pote est le bebou d'un mec, va vraiment falloir s'en débarasser, tu décide d'activer sa localisation snap pour agir et récupérer ta POTE, tu reçois donc une boussole qui pointe vers le mec de ta POTE.");
                MeilleurAmi.getInventory().addItem(PluginsItemStackUtils.getTracker());
            }

            if(SVUhc.getInstance().players().isLaMeilleureAmiFille(player2)) {
                Player MeilleurAmi = SVUhc.getInstance().players().getMeilleurAmiGarcon(player2);
                MeilleurAmi.sendMessage(SVUhc.getInstance().getTag() + "§7Oh SAPERLIPOFOUETTE, ta meilleure pote est le bebou d'un mec, va vraiment falloir s'en débarasser, tu décide d'activer sa localisation snap pour agir et récupérer ta POTE, tu reçois donc une boussole qui pointe vers le mec de ta POTE.");
                MeilleurAmi.getInventory().addItem(PluginsItemStackUtils.getTracker());
            }
        }
        
        if(couple.getTime() == 600 && couple.getState().equals("bebou")) {
            couple.setState("canard");

            player1.sendMessage(SVUhc.getInstance().getTag() + "§7On dirait bien que la relation avance, malheureusement, vous êtes devenu un véritable canard... Vous écopez donc de l'effet weakness 1 jusqu'à vos fillancaïlles.");
            player2.sendMessage(SVUhc.getInstance().getTag() + "§7On dirait bien que la relation avance, malheureusement, vous êtes devenu un véritable canard... Vous écopez donc de l'effet weakness 1 jusqu'à vos fillancaïlles.");

            player1.addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.WEAKNESS, 60 * 20 * 10, 0, false, false));
            player2.addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.WEAKNESS, 60 * 20 * 10, 0, false, false));

            couple.setTime(0);
        }

        if(couple.getTime() == 600 && couple.getState().equals("canard")) {
            couple.setState("fillance");
            player1.sendMessage(SVUhc.getInstance().getTag() + "§7On dirait bien que l'heure est à la demande...Vous venez de vous passer la bague au doigts, vous êtes fillancés avec §d§l" + player2.getName() + "§7, vous gagnez donc 2 coeurs définitifs supplémentaires et perdez votre effet weakness 1");
            player2.sendMessage(SVUhc.getInstance().getTag() + "§7On dirait bien que l'heure est à la demande...Vous venez de vous passer la bague au doigts, vous êtes fillancés avec §d§l" + player1.getName() + "§7, vous gagnez donc 2 coeurs définitifs supplémentaires et perdez votre effet weakness 1");

            player1.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player1.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 4);
            player2.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player2.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 4);

            couple.setTime(0);
        }
    }
}