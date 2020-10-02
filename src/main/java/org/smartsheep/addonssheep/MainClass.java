package org.smartsheep.addonssheep;

import SimpleCraft.SimpleCraftCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MainClass extends JavaPlugin {

    public static FileConfiguration MainConfig;

    @Override
    public void onLoad() { getLogger().info("AddonsSheep is loading... "); }

    @Override
    public void onDisable() {}

    @Override
    public void onEnable() {
        getLogger().info("AddonsSheep is loaded");
        getLogger().info("AddonsSheep version v0.2.92");
        getLogger().info("Welcome use AddonsSheep");

        Bukkit.getPluginCommand("movement").setExecutor(new CommandsCore());
        Bukkit.getPluginCommand("open").setExecutor(new CommandsCore());
        Bukkit.getPluginCommand("movement").setTabCompleter(new TabComplete());
        Bukkit.getPluginCommand("open").setTabCompleter(new TabComplete());

        Bukkit.getPluginManager().registerEvents(new ListenerCore(), this);

        saveDefaultConfig();
        MainClass.MainConfig = getPlugin(getClass()).getConfig();

        // 启用 simpleCraft subPlugin
        if (MainConfig.getInt("simpleCraft.simpleCraftEnable") == 0) {
            Bukkit.getLogger().info("[SimpleCraft] simpleCraft is Enable");
            Bukkit.getLogger().info("[SimpleCraft] simpleCraft version v0.19.2");

            Bukkit.getLogger().info("[SimpleCraft] simpleCraft Loading Stage1[Materials]...");
            SimpleCraftCore.InitSimpleCraft_Stage1();
            Bukkit.getLogger().info("[SimpleCraft] simpleCraft Loading Stage1[Materials] Complete");

            Bukkit.getLogger().info("[SimpleCraft] simpleCraft Loading Stage2[Tools]... ");
            SimpleCraftCore.InitSimpleCraft_Stage2();
            Bukkit.getLogger().info("[SimpleCraft] simpleCraft Loading Stage2[Tools] Complete");

            Bukkit.getLogger().info("[SimpleCraft] simpleCraft Loading Complete");
        }
    }
}
