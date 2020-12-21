package network.palace.photopass;

import lombok.Getter;
import network.palace.core.plugin.Plugin;
import network.palace.core.plugin.PluginInfo;

@PluginInfo(name = "PhotoPass", version = "1.0.0", depend = "Core", canReload = true)
public class Photopass extends Plugin {
    @Getter private static Photopass instance;

    @Override
    protected void onPluginEnable() throws Exception {
        instance = this;
        getLogger().info("Photopass loaded!");
    }

    @Override
    public void onPluginDisable() {

    }

}


