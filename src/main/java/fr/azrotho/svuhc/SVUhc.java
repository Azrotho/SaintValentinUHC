package fr.azrotho.svuhc;

import org.bukkit.plugin.java.JavaPlugin;

import com.iridium.iridiumcolorapi.IridiumColorAPI;

import fr.azrotho.svuhc.objects.SVPlayers;

public class SVUhc extends JavaPlugin {
    private static SVUhc INSTANCE;
    private SVPlayers players;
    private String tag = "§f§l[" + IridiumColorAPI.process("<GRADIENT:2C08BA>§lSaint-Valentin UHC</GRADIENT:028A97>") + "§f§l] §e§l➤ ";

    @Override
    public void onEnable() {
        INSTANCE = this;
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
