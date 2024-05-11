package fr.azrotho.svuhc.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.azrotho.svuhc.SVUhc;
import fr.azrotho.svuhc.utils.CoupleUpdate;

public class SVPlayers {
    public final List<SVPlayer> players;
    public final HashMap<Player, Player> rateau = new HashMap<>();
    public final List<SVCouple> couples = new ArrayList<>();
    public final List<String> stateOfCouple = new ArrayList<>(List.of(
        "kiff",
        "crush",
        "bebou",
        "canard",
        "fillance",
        "marie"
    ));

    public final List<String> cooldownsName = new ArrayList<>(List.of(
        "bisou",
        "colleserre",
        "uwu"
    ));

    public final HashMap<SVCouple, HashMap<String, Integer>> coupleCooldown = new HashMap<>();

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

    public void addRelation(Player player, Player target, String relation) {
        getPlayer(player).addRelation(target, relation);
    }

    public void addRelation(SVPlayer player, SVPlayer target, String relation) {
        player.addRelation(Bukkit.getPlayer(target.getUuid()), relation);
    }

    public Boolean hasRelation(Player player, Player target, String relation) {
        return getPlayer(player).getRelation(target).equals(relation);
    }

    public Boolean hasRelation(SVPlayer player, SVPlayer target, String relation) {
        return player.getRelation(Bukkit.getPlayer(target.getUuid())).equals(relation);
    }

    public Boolean isMeilleurAmi(Player player) {
        SVPlayer svPlayer = getPlayer(player);
        for(Player player2 : Bukkit.getOnlinePlayers()) {
            if(svPlayer.hasRelation(player2, "meilleur_ami")) {
                return true;
            }
        }
        return false;
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
                garconPlayer.sendMessage(SVUhc.getInstance().getTag() + "§fVous devez éliminer son amoureux afin de la récupérer, à moins de 50 blocs de lui, vous aurez speed 1, force 1 et résistance 1");
                garconPlayer.sendMessage(SVUhc.getInstance().getTag() + "§fSi vous arrivez à l'éliminer, vous garderez vos pouvoirs et récupérez §d§l" + fillePlayer.getName() + " §fet repartirez tous les deux au stade de kiff.");
                garconPlayer.sendMessage(SVUhc.getInstance().getTag() + "§fVous ne pouvez vous mettre en couple qu'avec §d§l" + fillePlayer.getName() +"§f, si celle-ci finis mariée, vous mourrirez instantanément.");
                garconPlayer.sendMessage(SVUhc.getInstance().getTag() + "§fSi votre meilleure amie vous mets un rateau, vous mourrirez instantanément");
                
            } else {
                designeMeilleurAmi();
            }
        }
    }

    public boolean isLaMeilleureAmiFille(Player player) {
        for(SVPlayer svPlayer : players) {
            if(svPlayer.getUuid().equals(player.getUniqueId())) {
                for(SVPlayer svTarget : players) {
                    if(svPlayer.hasRelation(svTarget, "meilleur_ami")) {
                        return svPlayer.getTeam().equals("fille");
                    }
                }
            }
        }
        return false;
    }

    public Player getMeilleurAmiGarcon(Player player) {
        for(SVPlayer svPlayer : players) {
            if(svPlayer.getUuid().equals(player.getUniqueId())) {
                for(SVPlayer svTarget : players) {
                    if(svPlayer.hasRelation(svTarget, "meilleur_ami")) {
                        if(svTarget.getTeam().equals("garcon")) {
                            return Bukkit.getPlayer(svTarget.getUuid());
                        }
                    }
                }
            }
        }
        return null;
    }

    public boolean MeilleurAmiAreInCouple(Player player) {
        for(SVPlayer svPlayer : players) {
            if(svPlayer.getUuid().equals(player.getUniqueId())) {
                for(SVPlayer svTarget : players) {
                    if(svPlayer.hasRelation(svTarget, "meilleur_ami")) {
                        for(String state : stateOfCouple) {
                            if(svPlayer.hasRelation(svTarget, state)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isCouple(Player player) {
        for(SVPlayer svPlayer : players) {
            if(svPlayer.getUuid().equals(player.getUniqueId())) {
                for(SVPlayer svTarget : players) {
                    for(String state : stateOfCouple) {
                        if(svPlayer.hasRelation(svTarget, state)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void addRateau(Player player, Player target) {
        rateau.put(player, target);
        rateau.put(target, player);
    }

    public boolean isInRateau(Player player, Player target) {
        return rateau.containsKey(player) && rateau.get(player).equals(target);
    }

    public void addTimeToAllCouples() {
        for(SVCouple couple : couples) {
            couple.setTime(couple.getTime() + 1);
            CoupleUpdate.update(couple);
        }
    }

    public List<SVCouple> getCouples() {
        return couples;
    }

    public void addTimeToCouple(SVCouple couple) {
        couple.setTime(couple.getTime() + 1);
        CoupleUpdate.update(couple);
    }

    public void removePlayerRelations(Player player) {
        SVPlayer svPlayer = getPlayer(player);
        for(SVPlayer svTarget : players) {
            if(svPlayer.hasRelation(svTarget)) {
                svTarget.removeRelation(Bukkit.getPlayer(svPlayer.getUuid()));
                svPlayer.removeRelation(Bukkit.getPlayer(svTarget.getUuid()));
            }
        }

        if(couples.size() > 0) {
            Iterator<SVCouple> iterator = couples.iterator();
            while (iterator.hasNext()) {
                SVCouple couple = iterator.next();
                if(couple.getPlayer1().getUuid().equals(player.getUniqueId()) || couple.getPlayer2().getUuid().equals(player.getUniqueId())) {
                    iterator.remove();
                }
            }
        }
    }

    public Player getCrushDeLaMeilleurePote(Player player) {
        for(SVPlayer svPlayer : players) {
            if(svPlayer.getUuid().equals(player.getUniqueId())) {
                for(SVPlayer svTarget : players) {
                    if(svPlayer.hasRelation(svTarget, "meilleur_ami")) {
                        for(SVCouple couple : couples) {
                            if(couple.getPlayer1().getUuid().equals(svTarget.getUuid())) {
                                return Bukkit.getPlayer(couple.getPlayer2().getUuid());
                            } else if(couple.getPlayer2().getUuid().equals(svTarget.getUuid())) {
                                return Bukkit.getPlayer(couple.getPlayer1().getUuid());
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public Boolean isCoupleMarie(Player player) {
        for(SVCouple couple : couples) {
            if(couple.getPlayer1().getUuid().equals(player.getUniqueId()) || couple.getPlayer2().getUuid().equals(player.getUniqueId())) {
                if(couple.getState().equals("marie")) {
                    return true;
                }
            }
        }
        return false;
    }

    public Player getOtherPlayerInCouple(Player player) {
        for(SVCouple couple : couples) {
            if(player == null) return null;
            if(couple.getPlayer1().getUuid().equals(player.getUniqueId())) {
                return Bukkit.getPlayer(couple.getPlayer2().getUuid());
            } else if(couple.getPlayer2().getUuid().equals(player.getUniqueId())) {
                return Bukkit.getPlayer(couple.getPlayer1().getUuid());
            }
        }
        return null;
    }

    public SVCouple getCouple(Player player) {
        for(SVCouple couple : couples) {
            if(couple.getPlayer1().getUuid().equals(player.getUniqueId()) || couple.getPlayer2().getUuid().equals(player.getUniqueId())) {
                return couple;
            }
        }
        return null;
    }

    public void initCoupleCooldown(SVCouple svCouple) {
        if(!coupleCooldown.containsKey(svCouple)) {
            coupleCooldown.put(svCouple, new HashMap<>());
        }
    }

    public void addCooldown(SVCouple couple, String state, int time) {
        if(coupleCooldown.containsKey(couple)) {
            coupleCooldown.get(couple).put(state, time);
        } else {
            coupleCooldown.put(couple, new HashMap<>());
            coupleCooldown.get(couple).put(state, time);
        }
    }


    public void removeCooldown(SVCouple couple, String state) {
        if(coupleCooldown.containsKey(couple)) {
            coupleCooldown.get(couple).remove(state);
        }
    }

    public int getCooldown(SVCouple couple, String state) {
        if(coupleCooldown.containsKey(couple)) {
            if(coupleCooldown.get(couple).containsKey(state)) {
                return coupleCooldown.get(couple).get(state);
            }
        }
        return 0;
    }

    public void updateCooldown(SVCouple svCouple) {
        for(String state : cooldownsName) {
            if(coupleCooldown.containsKey(svCouple)) {
                if(coupleCooldown.get(svCouple).containsKey(state)) {
                    coupleCooldown.get(svCouple).put(state, coupleCooldown.get(svCouple).get(state) - 1);
                    if(coupleCooldown.get(svCouple).get(state) <= 0) {
                        coupleCooldown.get(svCouple).remove(state);
                    }
                }
            }
        }
    }

    public boolean isInCooldown(SVCouple svCouple, String state) {
        if(coupleCooldown.containsKey(svCouple)) {
            if(coupleCooldown.get(svCouple).containsKey(state)) {
                return true;
            }
        }
        return false;
    }



}
