package network.palace.photopass.handlers.rides;

import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import network.palace.core.Core;
import network.palace.photopass.Photopass;
import network.palace.photopass.renderer.MapRender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Tom
 * @since 23/12/2020
 * @version 1.0.0
 */

public class SpaceMountain {
    Photopass instance = Photopass.getPlugin(network.palace.photopass.Photopass.class);
    FileConfiguration config = instance.getConfig();
    File smConfigFile = new File(instance.getDataFolder(), File.separator + "rides/sm.yml");
    FileConfiguration rideData = YamlConfiguration.loadConfiguration(smConfigFile);
    Integer frameNum = 0;

    public synchronized void createRidePhoto(SignActionEvent info) {
        if (info.getGroup().hasPassenger()) {
            info.getGroup().forEach(x -> {
                x.getEntity().getPlayerPassengers().forEach(y -> {
                    requestSMPhoto(y.getDisplayName());
                });
            });
        } else {
            Core.logInfo("[PhotoPass] Train was empty, generating empty");
        }
    }

    private void requestSMPhoto(String name) {
        MapRender makeMap = new MapRender();
        BufferedImage img = (BufferedImage) makeMap.getImageFromAPI("sm", name, config.getString("apiAccess"));
        Location frameLoc = new Location(Bukkit.getWorld(rideData.getString("world")), rideData.getDouble("frames." + frameNum.toString() + ".x"), rideData.getDouble("frames." + frameNum.toString() + ".y"), rideData.getDouble("frames." + frameNum.toString() + ".z"));
        makeMap.generatePhoto(frameLoc, img);
    }
}
