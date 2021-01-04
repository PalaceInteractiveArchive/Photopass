package network.palace.photopass.commands;

import network.palace.core.command.CommandException;
import network.palace.core.command.CommandMeta;
import network.palace.core.command.CoreCommand;
import network.palace.core.player.CPlayer;
import network.palace.core.player.Rank;

import org.bukkit.ChatColor;

/**
 * @author Tom
 * @since 04/01/2021
 * @version 1.0.0
 */
@CommandMeta(description = "Command used to manage PhotoPass", rank = Rank.SETTLER, aliases = {"pp"})
public class PhotoPassCommand extends CoreCommand {
    public PhotoPassCommand() {
        super("photopass");
        registerSubCommand(new ToggleCommand());
    }

    @Override
    protected void handleCommand(CPlayer player, String[] args) throws CommandException {
            player.sendMessage(ChatColor.GREEN + "PhotoPass Commands:");
            player.sendMessage(ChatColor.AQUA + "/photopass toggle " + ChatColor.GREEN + "- Toggle On/Off Receiving PhotoPass Photos");
    }

}
