package network.palace.photopass;

import com.bergerkiller.bukkit.tc.signactions.SignAction;
import lombok.Getter;
import network.palace.core.plugin.Plugin;
import network.palace.core.plugin.PluginInfo;
import network.palace.photopass.signactions.SignTakePhoto;


@PluginInfo(name = "PhotoPass", version = "1.0.0", depend = "Core", canReload = true)
public class Photopass extends Plugin {
    @Getter private static Photopass instance;

    @Override
    protected void onPluginEnable() throws Exception {
        instance = this;
        signSetup();
        getLogger().info("Photopass loaded!");
    }

    public void enable() {

    }

    @Override
    public void onPluginDisable() {

    }

    public void signSetup() {
        SignAction.register(new SignTakePhoto());

    }
}


