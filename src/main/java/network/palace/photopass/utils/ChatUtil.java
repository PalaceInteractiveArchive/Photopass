package network.palace.photopass.utils;

import network.palace.core.player.CPlayer;
import org.bukkit.ChatColor;

/**
 * @author Tom
 * @since 23/12/2020
 * @version 1.0.0
 */

public class ChatUtil {

    public static void sendPhotopassMessage(CPlayer player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[&bPhoto Pass&f] " + message));
    }

}

