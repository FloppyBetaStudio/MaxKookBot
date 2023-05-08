package ml.maxcraftmc.maxkookbot;

import io.github.kookybot.JavaBaseClass;
import io.github.kookybot.client.Client;
import io.github.kookybot.contract.Guild;
import io.github.kookybot.contract.Self;
import io.github.kookybot.contract.TextChannel;
import io.github.kookybot.events.EventHandler;
import io.github.kookybot.events.Listener;
import io.github.kookybot.events.channel.ChannelMessageEvent;
import org.bukkit.Bukkit;

import java.io.IOException;


public class KookMain {
    private static TextChannel workChannel;
    private static Client client;

    // Channel Message Listener / 频道消息监听器
    public static class ChannelMessageListener implements Listener {
        @SuppressWarnings("unused")
        @EventHandler
        // Received Channel Message Event / 收到频道消息事件
        public void onChannelMessage(ChannelMessageEvent event) {
            // Add a listener for channel messages / 添加一个监听器以侦听频道消息
            if (event.getChannel().getId().equals(workChannel.getId())) {
                String msg = "[Kook]["+event.getChannel().getName()+"]<"+event.getSender().getName()+">"+event.getContent();
                Bukkit.broadcast(msg, "bukkit.broadcast");

        }
    }

}

    public static void SendMessageToChannel(String msg) {
        workChannel.sendMessage(msg, null);
    }

    public static void main(String token, String ChannelID, String GuildID) throws IOException {
        // Create a new KOOK bot client / 创建一个新的 KOOK bot 客户端
        client = new Client(token, configure -> {
            // Register default Brigadier commands / 注册默认 Brigadier 命令
            configure.withDefaultCommands();
            return null;
        });
        // Start the KOOK bot client / 启动 KOOK bot 客户端
        @SuppressWarnings("unused")
        Self self = JavaBaseClass.utils.connectWebsocket(client);
        workChannel = new TextChannel(client, ChannelID, new Guild(client, GuildID));
        workChannel.sendMessage("机器人开机", null);
        // Add a listener for channel messages / 添加一个监听器以侦听频道消息
        client.getEventManager().addClassListener(new ChannelMessageListener());

    }



    public static void sendMessageAsync(String message) {
        // 创建一个新的线程来异步调用方法
        new Thread(() -> {
            SendMessageToChannel(message);
        }).start();
    }
}