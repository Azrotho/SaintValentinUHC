package fr.azrotho.svuhc.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.azrotho.svuhc.SVUhc;

public class SVPlayers {
    public final List<SVPlayer> players;

    public SVPlayers() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if(!containsPlayer(player)) {
            players.add(new SVPlayer(player));
        }
    }

    public SVPlayer getPlayer(Player player) {
        for(SVPlayer svPlayer : players) {
            if(svPlayer.getUuid().equals(player.getUniqueId())) {
                return svPlayer;
            }
        }
        return null;
    }

    public Boolean containsPlayer(Player player) {
        for(SVPlayer svPlayer : players) {
            if(svPlayer.getUuid().equals(player.getUniqueId())) {
                return true;
            }
        }
        return false;
    }

    public Boolean isGarcon(Player player) {
        for(SVPlayer svPlayer : players) {
            if(svPlayer.getUuid().equals(player.getUniqueId())) {
                return svPlayer.getTeam().equals("garcon");
            }
        }
        return false;
    }

    public Boolean isFille(Player player) {
        for(SVPlayer svPlayer : players) {
            if(svPlayer.getUuid().equals(player.getUniqueId())) {
                return svPlayer.getTeam().equals("fille");
            }
        }
        return false;
    }

    public void setMeilleurAmi(Player player, Player target) {
        getPlayer(player).addRelation(target, "meilleur_ami");
    }

    public void setMeilleurAmi(SVPlayer player, SVPlayer target) {
        player.addRelation(Bukkit.getPlayer(target.getUuid()), "meilleur_ami");
    }

    public Boolean hasRelation(Player player, Player target, String relation) {
        return getPlayer(player).getRelation(target).equals(relation);
    }

    public Boolean hasRelation(SVPlayer player, SVPlayer target, String relation) {
        return player.getRelation(Bukkit.getPlayer(target.getUuid())).equals(relation);
    }

    public void designeMeilleurAmi() {
        // Désigne aléatoirement 2 Joueurs qui sont 1 garçon et 1 fille et les mets en relation "meilleur_ami", vérifier que les 2 joueurs ne sont pas déjà en relation
        List<SVPlayer> garcons = new ArrayList<>();
        List<SVPlayer> filles = new ArrayList<>();
        for(SVPlayer svPlayer : players) {
            if(svPlayer.getTeam().equals("garcon")) {
                garcons.add(svPlayer);
            } else if(svPlayer.getTeam().equals("fille")) {
                filles.add(svPlayer);
            }
        }
        if(garcons.size() >= 1 && filles.size() >= 1) {
            SVPlayer garcon = garcons.get((int) (Math.random() * garcons.size()));
            SVPlayer fille = filles.get((int) (Math.random() * filles.size()));
            if(!garcon.hasRelation(fille, "meilleur_ami") && !fille.hasRelation(garcon, "meilleur_ami")) {
                garcon.addRelation(Bukkit.getPlayer(fille.getUuid()), "meilleur_ami");
                fille.addRelation(Bukkit.getPlayer(garcon.getUuid()), "meilleur_ami");
                Player garconPlayer = Bukkit.getPlayer(garcon.getUuid());
                Player fillePlayer = Bukkit.getPlayer(fille.getUuid());

                garconPlayer.sendMessage(SVUhc.getInstance().getTag() + "§fVous êtes meilleur/e ami/e, votre objectif est de pécho §d§l" + fillePlayer.getName());
                garconPlayer.sendMessage(SVUhc.getInstance().getTag() + "§fTant que vous n'êtes pas avec elle, vous aurez slowness 1 et 8 coeurs seulement.");
                garconPlayer.sendMessage(SVUhc.getInstance().getTag() + "§fLorsque votre meilleure amie sera en mode bebou sur quelqu'un, vous receverez une boussole pointant vers son bebou.");
                garconPlayer.sendMessage(SVUhc.getInstance().getTag() + "§fVous devez éliminer son amoureux afin de la récupérer, à moins de 50 blocs de lui, vous aurez speed 1, force 1 et 2 coeurs supplémentaires");
                garconPlayer.sendMessage(SVUhc.getInstance().getTag() + "§fSi vous arrivez à l'éliminer, vous garderez vos pouvoirs et récupérez §d§l" + fillePlayer.getName() + " §fet repartirez tous les deux au stade de kiff.");
                garconPlayer.sendMessage(SVUhc.getInstance().getTag() + "§fVous ne pouvez vous mettre en couple qu'avec §d§l" + fillePlayer.getName() +"§f, si celle-ci finis mariée, vous mourrirez instantanément.");
                garconPlayer.sendMessage(SVUhc.getInstance().getTag() + "§fSi votre meilleure amie vous mets un rateau, vous mourrirez instantanément");
                
            } else {
                designeMeilleurAmi();
            }
        }
    }
}
