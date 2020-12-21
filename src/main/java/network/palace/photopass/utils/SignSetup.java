package network.palace.photopass.utils;

import com.bergerkiller.bukkit.tc.signactions.SignAction;
import network.palace.photopass.signactions.SignTakePhoto;

import java.util.ArrayList;
import java.util.List;

import static com.bergerkiller.bukkit.tc.signactions.SignAction.register;

public class SignSetup {
    private static List<SignAction> actions;

    public static void init() {
        actions = new ArrayList<>();
        register(new SignTakePhoto());
    }
}
