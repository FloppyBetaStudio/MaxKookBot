package ml.maxcraftmc.maxkookbot;

import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static org.bukkit.event.EventPriority.MONITOR;

public class MCListener implements Listener{
    @EventHandler(priority = MONITOR)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (event.isCancelled()) {
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
        String message = player.getName() + " 获得了成就 " + event.getAdvancement().getDisplay().title();
        KookMain.sendMessageAsync(message);
    }
}


