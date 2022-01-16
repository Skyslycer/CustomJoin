package de.skyslycer.customjoin.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.MinecraftKey;
import de.skyslycer.customjoin.CustomJoin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    private CustomJoin plugin;

    public JoinQuitListener(CustomJoin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("join-message").replace("%player%", player.getName())
        ));

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = manager.createPacket(PacketType.Play.Server.CUSTOM_SOUND_EFFECT);

        packet.getMinecraftKeys()
                .write(0, getMinecraftKey(plugin.getConfig()));
        packet.getSoundCategories()
                .write(0, EnumWrappers.SoundCategory.AMBIENT);
        packet.getIntegers()
                .write(0, player.getLocation().getBlockX() * 8)
                .write(1, player.getLocation().getBlockY() * 8)
                .write(2, player.getLocation().getBlockZ() * 8);
        packet.getFloat()
                .write(0, 1000000.0f)
                .write(1, 1.0f);

        try {
            manager.sendServerPacket(player, packet);
        } catch (Exception exception) {
            plugin.getLogger().warning("An error occurred while trying to send a custom sound! Please report the following error:");
            exception.printStackTrace();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("quit-message").replace("%player%", event.getPlayer().getName())
        ));
    }

    private MinecraftKey getMinecraftKey(FileConfiguration config) {
        String code = config.getString("custom-sound");

        if (code == null) {
            return null;
        }

        boolean split = code.split(":").length >= 2;

        String prefix = "oraxen";
        if (split) {
            prefix = code.split(":")[0];
        }

        String key = code;
        if (split) {
            key = code.split(":")[1];
        }

        return new MinecraftKey(prefix, key);
    }

}
