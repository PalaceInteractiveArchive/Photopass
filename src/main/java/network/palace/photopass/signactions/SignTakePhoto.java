package network.palace.photopass.signactions;

import com.bergerkiller.bukkit.tc.Permission;
import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import com.bergerkiller.bukkit.tc.events.SignChangeActionEvent;
import com.bergerkiller.bukkit.tc.signactions.SignAction;
import com.bergerkiller.bukkit.tc.signactions.SignActionType;
import com.bergerkiller.bukkit.tc.utils.SignBuildOptions;

import org.bukkit.Bukkit;

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
                    Bukkit.getLogger().info("Yes it worked!");
                    break;
                default:
                    Bukkit.getLogger().info("Yes it worked but not sm!");
                    break;
            }
        }
    }

    @Override
    public boolean build(SignChangeActionEvent event) {
        return SignBuildOptions.create()
                .setPermission(Permission.BUILD_SPAWNER) // We don't have a specific permission, so putting it under this will do (for now)
                .setName("train photopass sign")
                .setDescription("generates a photo based on the parsed arguments")
                .setHelpURL("https://tomr.dev/pal/tcc/photopass")
                .handle(event.getPlayer());
    }

    @Override
    public boolean canSupportRC() {
        return false;
    }


}

