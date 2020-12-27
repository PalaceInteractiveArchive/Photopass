package network.palace.photopass.handlers.rides;

import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import network.palace.core.Core;
import network.palace.photopass.Photopass;
import network.palace.photopass.renderer.MapRender;
import network.palace.photopass.utils.ChatUtil;
import network.palace.photopass.utils.ImageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

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
        Bukkit.getScheduler().runTask(instance, () -> {
            if (info.getGroup().hasPassenger()) {
                info.getGroup().forEach(x -> {
                    x.getEntity().getPlayerPassengers().forEach(y -> {
                        try {
                            requestSMPhoto(y.getDisplayName(), y);
                        } catch (Exception e) {
                            Core.logInfo("[PhotoPass] Error Rendering SpaceMountain Picture: " + e.getLocalizedMessage());
                        }
                    });
                });
            } else {
                Core.logInfo("[PhotoPass] Train was empty, generating empty");
            }
        });
    }

    private void requestSMPhoto(String name, Player p) throws Exception {
        MapRender makeMap = new MapRender();
        ImageUtils imgUtils = new ImageUtils();
        Image img = makeMap.getImageFromAPI("SpaceMountain", name, config.getString("apiAccess"));
        BufferedImage buffImg =  imgUtils.convertToBufferedImage(imgUtils.resizeImage(img, 128, 128));
        Location frameLoc = new Location(Bukkit.getWorld(rideData.getString("world")), rideData.getDouble("frames." + frameNum.toString() + ".x"), rideData.getDouble("frames." + frameNum.toString() + ".y"), rideData.getDouble("frames." + frameNum.toString() + ".z"));
        makeMap.generatePhoto(frameLoc, buffImg);
        ChatUtil.sendPhotopassMessage(p, "Smile! Your Photo will be available at the exit!");
    }
}
