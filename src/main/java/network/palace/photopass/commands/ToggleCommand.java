package network.palace.photopass.commands;

import network.palace.core.command.CommandException;
import network.palace.core.command.CommandMeta;
import network.palace.core.command.CoreCommand;
import network.palace.core.player.CPlayer;
import network.palace.photopass.utils.ChatUtil;
import network.palace.photopass.utils.MongoManager;

/**
 * @author Tom
 * @since 04/01/2021
 * @version 1.0.0
 */
@CommandMeta(description = "Toggle Ridephotos On/Off")
public class ToggleCommand extends CoreCommand {

    public ToggleCommand() {
        super("toggle");
    }

    @Override
    protected void handleCommand(CPlayer player, String[] args) throws CommandException {
        MongoManager mm = new MongoManager();
        boolean toggle = mm.switchToggle(player.getBukkitPlayer());

        if (toggle) {
            ChatUtil.sendPhotopassMessage(player.getBukkitPlayer(), "Toggled On PhotoPass Photos");
        } else {
            ChatUtil.sendPhotopassMessage(player.getBukkitPlayer(), "Toggled Off PhotoPass Photos");
        }
    }
}
