package fr.azrotho.svuhc;

import org.bukkit.plugin.java.JavaPlugin;

import com.iridium.iridiumcolorapi.IridiumColorAPI;

import fr.azrotho.svuhc.commands.NickCommand;
import fr.azrotho.svuhc.commands.TeamCommand;
import fr.azrotho.svuhc.objects.SVPlayers;
import fr.azrotho.svuhc.utils.NickManager;

public class SVUhc extends JavaPlugin {
    private static SVUhc INSTANCE;
    private SVPlayers players;
    private String tag = "§f§l[" + IridiumColorAPI.process("<GRADIENT:2C08BA>§lSaint-Valentin UHC</GRADIENT:028A97>") + "§f§l] §e§l➤ ";
    private NickManager nickManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        getCommand("team").setExecutor(new TeamCommand());
        getCommand("nick").setExecutor(new NickCommand());
        nickManager = new NickManager();
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

    public NickManager getNickManager() {
        return this.nickManager;
    }
}
