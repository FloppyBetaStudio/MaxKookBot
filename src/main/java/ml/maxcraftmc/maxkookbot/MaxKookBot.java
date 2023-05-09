package ml.maxcraftmc.maxkookbot;


import org.bukkit.advancement.Advancement;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import static org.bukkit.event.EventPriority.MONITOR;

public final class MaxKookBot extends JavaPlugin {
    public static Logger log;
    @Override
    public void onEnable() {
        // Plugin startup logic
        log = this.getLogger();
        File configFile = new File(this.getDataFolder() + File.separator + "config.yml");
        FileConfiguration pluginConfiguration = YamlConfiguration.loadConfiguration(configFile);
        if ((pluginConfiguration.get("token", "YourTokenHere") == "YourTokenHere") || (pluginConfiguration.get("channelID", "ChannelIDHere") == "ChannelIDHere") || (pluginConfiguration.get("guildID", "GuildIDHere") == "GuildIDHere")) {
            pluginConfiguration.set("token", "YourTokenHere");
            pluginConfiguration.set("channelID", "'ChannelIDHere'");
            pluginConfiguration.set("guildID", "'GuildIDHere'");
            try {
                pluginConfiguration.save(configFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                KookMain.main((String) pluginConfiguration.get("token"), pluginConfiguration.get("channelID").toString(), pluginConfiguration.get("guildID").toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        getServer().getPluginManager().registerEvents(new MCListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        KookMain.SendMessageToChannel("机器人关机");
    }



}
