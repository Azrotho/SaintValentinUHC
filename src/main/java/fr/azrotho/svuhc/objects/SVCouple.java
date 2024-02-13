package fr.azrotho.svuhc.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SVCouple {
    private final SVPlayer player1;
    private final SVPlayer player2;
    private String state;
    private int time;

    public SVCouple(SVPlayer player1, SVPlayer player2, String state) {
        this.player1 = player1;
        this.player2 = player2;
        this.state = state;
        this.time = 0;
    }

    public SVPlayer getPlayer1() {
        return player1;
    }

    public SVPlayer getPlayer2() {
        return player2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Player getOtherPlayer(Player player) {
        if(player.getUniqueId().equals(player1.getUuid())) {
            return Bukkit.getPlayer(player2.getUuid());
        } else {
            return Bukkit.getPlayer(player1.getUuid());
        }
    }
}
