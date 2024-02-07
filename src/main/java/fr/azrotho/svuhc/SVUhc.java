package fr.azrotho.svuhc;

import org.bukkit.plugin.java.JavaPlugin;

import com.iridium.iridiumcolorapi.IridiumColorAPI;

import fr.azrotho.svuhc.commands.TeamCommand;
import fr.azrotho.svuhc.listeners.OnJoin;
import fr.azrotho.svuhc.objects.SVPlayers;

public class SVUhc extends JavaPlugin {
    private static SVUhc INSTANCE;
    private SVPlayers players;
    private String tag = "§f§l[" + IridiumColorAPI.process("<GRADIENT:FF00EF>§lSaint-Valentin UHC</GRADIENT:5B00FF>") + "§f§l] §e§l➤ ";

    @Override
    public void onEnable() {
        INSTANCE = this;
        getCommand("team").setExecutor(new TeamCommand());
        this.players = new SVPlayers();

        getServer().getPluginManager().registerEvents(new OnJoin(), this);
    }

    @Override
    public void onDisable() {
        
    }

    public static SVUhc getInstance() {
        return INSTANCE;
    }

    public SVPlayers players() {
        return this.players;
    }

    public String getTag() {
        return this.tag;
    }
}
