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
        getServer().getPluginManager().registerEvents(new BotListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        KookMain.SendMessageToChannel("机器人关机");
    }

    public class BotListener implements Listener {
        @EventHandler(priority = MONITOR)
        public void onPlayerChat(AsyncPlayerChatEvent event) {
            if (event.isCancelled() ){
                return;
            }
            String s = "<" + event.getPlayer().getName() + ">" + event.getMessage();
            KookMain.sendMessageAsync(s);


        }

        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent event) {
            // 玩家加入游戏
            Player player = event.getPlayer();
            String message = player.getName() + " 加入了游戏!";
            KookMain.sendMessageAsync(message);
        }

        @EventHandler
        public void onPlayerQuit(PlayerQuitEvent event) {
            // 玩家退出游戏
            Player player = event.getPlayer();
            String message = player.getName() + " 退出了游戏!";
            KookMain.sendMessageAsync(message);
        }

        // 玩家死亡
        @EventHandler
        public void onPlayerDeath(PlayerDeathEvent event) {
            Player player = event.getEntity();
            String deathMessage = player.getName() + " 死了!";
            if (event.getDeathMessage() != null) {
                deathMessage += " 死因:" + event.getDeathMessage();
            }
            KookMain.sendMessageAsync(deathMessage);
        }

        // 玩家获得成就
        @EventHandler
        public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
            Player player = event.getPlayer();
            Advancement achievement = event.getAdvancement();
            String message = player.getName() + " 获得了成就 " + achievement.getDisplay();
            KookMain.sendMessageAsync(message);
        }
    }


}
