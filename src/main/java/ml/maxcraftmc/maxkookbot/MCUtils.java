package ml.maxcraftmc.maxkookbot;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class MCUtils {
    public static String getOnlinePlayers() {
        int onlineCount = Bukkit.getOnlinePlayers().size();
        int maxPlayers = Bukkit.getMaxPlayers();
        String onlinePlayers = "在线人数 " + onlineCount + "/" + maxPlayers + "\n";

        for (Player player : Bukkit.getOnlinePlayers()) {
            onlinePlayers += player.getName() + "\n";
        }

        return onlinePlayers;
    }
}
