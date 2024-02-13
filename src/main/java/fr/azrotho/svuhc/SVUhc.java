package fr.azrotho.svuhc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.azrotho.svuhc.commands.BisouCommand;
import fr.azrotho.svuhc.commands.ColleSerreCommand;
import fr.azrotho.svuhc.commands.CoupleCommand;
import fr.azrotho.svuhc.commands.StartCommand;
import fr.azrotho.svuhc.commands.TeamCommand;
import fr.azrotho.svuhc.commands.UWUCommand;
import fr.azrotho.svuhc.listeners.FastSmelting;
import fr.azrotho.svuhc.listeners.HasteyBoy;
import fr.azrotho.svuhc.listeners.OnDamage;
import fr.azrotho.svuhc.listeners.OnDeath;
import fr.azrotho.svuhc.listeners.OnInventoryClose;
import fr.azrotho.svuhc.listeners.OnInventoryInteraction;
import fr.azrotho.svuhc.listeners.OnJoin;
import fr.azrotho.svuhc.listeners.Timber;
import fr.azrotho.svuhc.objects.SVPlayers;
import fr.azrotho.svuhc.runnable.CatEyesRunnable;
import fr.azrotho.svuhc.runnable.CooldownCoupleUpdate;
import fr.azrotho.svuhc.runnable.RivalRunnable;
import fr.azrotho.svuhc.runnable.TimeCoupleRunnable;
import fr.azrotho.svuhc.runnable.TrackingRunnable;

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
        getCommand("bisou").setExecutor(new BisouCommand());
        getCommand("uwu").setExecutor(new UWUCommand());
        getCommand("colleserre").setExecutor(new ColleSerreCommand());
        this.players = new SVPlayers();

        Bukkit.getServer().getPluginManager().registerEvents(new OnJoin(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new FastSmelting(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Timber(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new HasteyBoy(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnInventoryClose(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnInventoryInteraction(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnDeath(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnDamage(), this);

        tag = "§f§l[§d§lSaint-Valentin UHC§f§l] §e§l➤ ";
        
        CatEyesRunnable catEyesRunnable = new CatEyesRunnable();
        catEyesRunnable.runTaskTimer(this, 0, 0);

        TimeCoupleRunnable timeCoupleRunnable = new TimeCoupleRunnable();
        timeCoupleRunnable.runTaskTimer(this, 0, 20);

        TrackingRunnable trackingRunnable = new TrackingRunnable();
        trackingRunnable.runTaskTimer(this, 0, 20);


        CooldownCoupleUpdate cooldownCoupleUpdate = new CooldownCoupleUpdate();
        cooldownCoupleUpdate.runTaskTimer(this, 0, 20);

        RivalRunnable rivalRunnable = new RivalRunnable();
        rivalRunnable.runTaskTimer(this, 0, 5);
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
