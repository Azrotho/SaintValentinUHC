package fr.azrotho.svuhc.objects;

public class SVCouple {
    private final SVPlayer player1;
    private final SVPlayer player2;
    private final String state;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
