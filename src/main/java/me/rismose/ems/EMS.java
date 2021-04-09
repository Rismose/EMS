package me.rismose.ems;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class EMS extends JavaPlugin implements Listener {

    public static String Version;
    public static String ZombieNormalSpawn;
    public static String ZombieHelmetSpawn;
    public static String ZombieChestSpawn;
    public static String ZombieLeggingSpawn;
    public static String ZombieBootSpawn;
    public static String Permission;
    public static String NonPlayer;

    public EMS() {
        Version = this.getConfig().getString("Version");
        ZombieNormalSpawn = this.getConfig().getString("ZombieNormalSpawn");
        ZombieHelmetSpawn = this.getConfig().getString("ZombieHelmetSpawn");
        ZombieChestSpawn = this.getConfig().getString("ZombieChestSpawn");
        ZombieLeggingSpawn = this.getConfig().getString("ZombieLeggingSpawn");
        ZombieBootSpawn = this.getConfig().getString("ZombieBootSpawn");
        Permission = this.getConfig().getString("Permission");
        NonPlayer = this.getConfig().getString("NonPlayer");
    }

    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        this.reloadConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getConsoleSender().sendMessage("§7EMS Starting...");
        Bukkit.getConsoleSender().sendMessage("§7EMS Version " + Version);
        Bukkit.getConsoleSender().sendMessage("§7EMS Has Been Started!");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("ems").setExecutor(new Menu());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§7EMS Shut Down Unexpectedly");
    }
}
