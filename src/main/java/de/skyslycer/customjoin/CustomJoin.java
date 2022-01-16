package de.skyslycer.customjoin;

import de.skyslycer.customjoin.commands.ReloadCommand;
import de.skyslycer.customjoin.listeners.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomJoin extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(this), this);
        getCommand("customjoinreload").setExecutor(new ReloadCommand(this));
    }

}
