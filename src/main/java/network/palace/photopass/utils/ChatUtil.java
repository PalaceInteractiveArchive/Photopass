package network.palace.photopass.utils;

import network.palace.core.player.CPlayer;
import org.bukkit.ChatColor;

public class ChatUtil {

    public static void sendPhotopassMessage(CPlayer player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[&bPhoto Pass&f] " + message));
    }

}

