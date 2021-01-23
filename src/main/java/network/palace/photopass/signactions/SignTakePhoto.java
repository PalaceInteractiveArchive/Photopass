package network.palace.photopass.signactions;

import com.bergerkiller.bukkit.tc.Permission;
import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import com.bergerkiller.bukkit.tc.utils.SignBuildOptions;

import network.palace.core.Core;
import network.palace.core.player.CPlayer;
import network.palace.core.player.Rank;
import network.palace.photopass.handlers.rides.*;
import org.bukkit.entity.Player;

/**
 * @author Tom
 * @since 23/12/2020
 * @version 1.0.0
*/

public class SignTakePhoto extends SignAction {

    @Override
    public boolean match(SignActionEvent info) {
        return info.isType("photopass");
    }

    @Override
    public void execute(SignActionEvent info) {
        if (!info.isPowered()) return;
        if (info.isTrainSign() && info.isAction(SignActionType.REDSTONE_ON, SignActionType.GROUP_ENTER) && info.hasGroup()) {
            switch (info.getLine(2)) {
                case "sm":
                    Core.logInfo("[Photo Pass] Generating Ridephoto for 'sm'");
                    String side = info.getLine(3);
                    if (side.equals("1")) {
                        SpaceMountain sm = new SpaceMountain();
                        sm.createRidePhoto(info);
                    } else if (side.equals("2")) {
                        SpaceMountain2 sm2 = new SpaceMountain2();
                        sm2.createRidePhoto(info);
                    } else {
                        Core.logInfo("Error. SM side was not specified");
                    }
                    break;
                case "tt":
                    Core.logInfo("[Photo Pass] Generating Ridephoto for 'tt'");
                    TestTrack tt = new TestTrack();
                    tt.createRidePhoto(info);
                    break;
                case "hm":
                    Core.logInfo("[Photo Pass] Generating Ridephoto for 'hm'");
                    HauntedMansion hm = new HauntedMansion();
                    hm.createRidePhoto(info);
                    break;
                case "buzz":
                    Core.logInfo("[Photo Pass] Generating Ridephoto from 'buzz'");
                    BuzzLightyear bz = new BuzzLightyear();
                    bz.createRidePhoto(info);
                    break;
                case "rrc":
                    Core.logInfo("[Photo Pass] Generating Ridephoto from 'rrc'");
                    RockNRoller rrc = new RockNRoller();
                    rrc.createRidePhoto(info);
                    break;
                default:
                    Core.logInfo("[Photo Pass] Photopass sign with invalid setup: " + info.getLine(2));
                    break;
            }
        }
    }

    @Override
    public boolean build(SignChangeActionEvent event) {
        Player pSpigot = event.getPlayer();
        CPlayer p = Core.getPlayerManager().getPlayer(pSpigot);
        if (p.getRank().getRankId() >= Rank.COORDINATOR.getRankId()) {
            return SignBuildOptions.create()
                    .setPermission(Permission.BUILD_SPAWNER) // We don't have a specific permission, so putting it under this will do (for now)
                    .setName("PhotoPass Sign")
                    .setDescription("generate a photo based on the parsed arguments")
                    .setHelpURL("https://tomr.dev/pal/tcc/photopass")
                    .handle(event.getPlayer());
        } else {
            p.sendMessage("Please don't try to place these. You do not have permission");
            return false;
        }
    }

    @Override
    public boolean canSupportRC() {
        return false;
    }


}

