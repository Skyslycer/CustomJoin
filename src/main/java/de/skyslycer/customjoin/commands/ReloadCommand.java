package de.skyslycer.customjoin.commands;

import de.skyslycer.customjoin.CustomJoin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private CustomJoin plugin;

    public ReloadCommand(CustomJoin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("customjoin.reload")) {
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully reloaded the config!"));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have enough permissions to run this command!"));
        }
        return true;
    }

}
