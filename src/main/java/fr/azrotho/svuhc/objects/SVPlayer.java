package fr.azrotho.svuhc.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.HashMap;


public class SVPlayer {
    private UUID uuid;
    private String team;
    private HashMap<Player, String> relations = new HashMap<>();

    public SVPlayer(Player player) {
        this.uuid = player.getUniqueId();
        this.relations = new HashMap<>();
        this.team = "None";
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getTeam() {
        return this.team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void addRelation(Player player, String relation) {
        this.relations.put(player, relation);
    }

    public void removeRelation(Player player) {
        this.relations.remove(player);
    }

    public String getRelation(Player player) {
        if(!this.relations.containsKey(player)) return "None";
        return this.relations.get(player);
    }

    public boolean hasRelation(Player player) {
        return this.relations.containsKey(player);
    }

    public boolean hasRelation(Player player, String relation) {
        return this.relations.containsKey(player) && this.relations.get(player).equals(relation);
    }

    public boolean hasRelation(SVPlayer player, String relation) {
        return this.relations.containsKey(Bukkit.getPlayer(player.getUuid())) && this.relations.get(Bukkit.getPlayer(player.getUuid())).equals(relation);
    }

    public boolean hasRelation(SVPlayer player) {
        return this.relations.containsKey(Bukkit.getPlayer(player.getUuid()));
    }
}
