package fr.azrotho.svuhc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.azrotho.svuhc.commands.CoupleCommand;
import fr.azrotho.svuhc.commands.StartCommand;
import fr.azrotho.svuhc.commands.TeamCommand;
import fr.azrotho.svuhc.listeners.FastSmelting;
import fr.azrotho.svuhc.listeners.HasteyBoy;
import fr.azrotho.svuhc.listeners.OnInventoryClose;
import fr.azrotho.svuhc.listeners.OnInventoryInteraction;
import fr.azrotho.svuhc.listeners.OnJoin;
import fr.azrotho.svuhc.listeners.Timber;
import fr.azrotho.svuhc.objects.SVPlayers;
import fr.azrotho.svuhc.runnable.CatEyesRunnable;

public class SVUhc extends JavaPlugin {
    private static SVUhc INSTANCE;
    private SVPlayers players;
    private String tag;
    private Boolean isStarted = false;

    @Override
    public void onEnable() {
        INSTANCE = this;
        getCommand("team").setExecutor(new TeamCommand());
        getCommand("start").setExecutor(new StartCommand());
        getCommand("couple").setExecutor(new CoupleCommand());
        this.players = new SVPlayers();

        Bukkit.getServer().getPluginManager().registerEvents(new OnJoin(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new FastSmelting(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Timber(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new HasteyBoy(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnInventoryClose(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnInventoryInteraction(), this);

        tag = "§f§l[§d§lSaint-Valentin UHC§f§l] §e§l➤ ";
        
        CatEyesRunnable catEyesRunnable = new CatEyesRunnable();
        catEyesRunnable.runTaskTimer(this, 0, 0);
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

    public Boolean isStarted() {
        return this.isStarted;
    }

    public void setStarted(Boolean isStarted) {
        this.isStarted = isStarted;
    }
}
