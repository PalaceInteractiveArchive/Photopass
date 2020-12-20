package network.palace.photopass;

import lombok.Getter;
import network.palace.core.Core;
import network.palace.core.player.CPlayer;
import network.palace.core.plugin.Plugin;
import network.palace.core.plugin.PluginInfo;


@PluginInfo(name = "Photopass", version = "1.0.0", depend = {"Core"}, canReload = true, apiversion = "1.12")
public class Photopass extends Plugin {
    @Getter private static Photopass instance;

    @Override
    public void onPluginEnable() {
        instance = this;
        registerCommands();

    }

    @Override
    public void onPluginDisable() {

    }

    private void registerCommands() {

    }

}
