package network.palace.photopass.commands;

import network.palace.core.command.CommandMeta;
import network.palace.core.command.CoreCommand;
import network.palace.core.player.Rank;

@CommandMeta(description = "Main Ridephoto", rank = Rank.DEVELOPER)
public class RidePhoto extends CoreCommand {

    public RidePhoto() {
        super("ridephoto");
    }

}
