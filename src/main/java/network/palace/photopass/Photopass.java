package network.palace.photopass;

import static com.bergerkiller.bukkit.tc.signactions.SignAction.register;
import lombok.Getter;
import network.palace.core.plugin.Plugin;
import network.palace.core.plugin.PluginInfo;
import network.palace.photopass.signactions.SignTakePhoto;
import org.bukkit.configuration.file.FileConfiguration;


/**
 * @author Tom
 * @since 23/12/2020
 * @version 1.0.0
 */

@PluginInfo(name = "PhotoPass", version = "1.0.0", depend = {"Core", "Train_Carts", "BKCommonLib"}, canReload = true)
public class Photopass extends Plugin {
    @Getter private static Photopass instance;

    @Override
    protected void onPluginEnable() throws Exception {
        instance = this;
        signSetup();
        getLogger().info("Photopass loaded!");
    }

    @Override
    public FileConfiguration getConfig() {
        return this.getConfig();
    }

    @Override
    public void onPluginDisable() {

    }

    public void signSetup() {
        register(new SignTakePhoto());
    }

    public void configSetup() {
        FileConfiguration config = this.getConfig();
        config.addDefault("apiAccess", "");
    }
}


