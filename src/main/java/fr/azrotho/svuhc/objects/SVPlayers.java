package fr.azrotho.svuhc.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class SVPlayers {
    public final List<SVPlayer> players;

    public SVPlayers() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if(!players.contains(player)) {
            players.add(new SVPlayer(player));
        }
    }
}
