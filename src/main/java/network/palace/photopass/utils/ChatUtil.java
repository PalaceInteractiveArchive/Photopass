package network.palace.photopass.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author Tom
 * @since 23/12/2020
 * @version 1.0.0
 */

public class ChatUtil {

    public static void sendPhotopassMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[&bPhoto Pass&f] &9" + message));
    }

}

