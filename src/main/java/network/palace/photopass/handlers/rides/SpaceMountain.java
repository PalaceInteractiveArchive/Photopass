package network.palace.photopass.handlers.rides;

import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import network.palace.core.Core;

public class SpaceMountain {

    public void createRidePhoto(SignActionEvent info) {
        if (info.getGroup().hasPassenger()) {
            info.getGroup().forEach(x -> {
                x.getEntity().getPlayerPassengers().forEach(y -> {
                    requestSMPhoto(y.getDisplayName());
                });
            });
        } else {
            Core.logInfo("Train was empty, generating empty");
        }
    }

    private void requestSMPhoto(String name) {
        Core.logInfo(name);
    }
}
