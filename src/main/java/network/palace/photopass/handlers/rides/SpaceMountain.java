package network.palace.photopass.handlers.rides;

import com.bergerkiller.bukkit.tc.events.SignActionEvent;
import network.palace.core.Core;
import network.palace.photopass.Photopass;
import network.palace.photopass.handlers.ImgurUpload;
import network.palace.photopass.renderer.MapRender;
import network.palace.photopass.utils.ChatUtil;
import network.palace.photopass.utils.DateFormat;
import network.palace.photopass.utils.MongoManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static network.palace.photopass.utils.ImageUtils.convertToBufferedImage;
import static network.palace.photopass.utils.ImageUtils.resizeImage;

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
    public static Integer frameNum = 0;

    public synchronized void createRidePhoto(SignActionEvent info) {
        Bukkit.getScheduler().runTaskAsynchronously(instance, () -> {
            if (info.getGroup().hasPassenger()) {
                info.getGroup().forEach(x -> {
                    AtomicReference<Boolean> upload = new AtomicReference<>(true);
                    x.getEntity().getPlayerPassengers().forEach(y -> {
//                        try {
                            requestSMPhoto(y.getDisplayName(), y, upload.get(), x.getEntity().getPlayerPassengers());
                            if (upload.get()) {
                                upload.set(false);
                            }
//                        } catch (Exception e) {
//                            Core.logInfo("[PhotoPass] Error Rendering SpaceMountain Picture: " + e.getLocalizedMessage());
//                        }
                    });
                });
            } else {
                Core.logInfo("[PhotoPass] Train was empty, generating empty");
            }
        });
    }

    private void requestSMPhoto(String name, Player p, Boolean uploadImgur, List<Player> players) {
        MapRender makeMap = new MapRender();
        Image img = makeMap.getImageFromAPI("SpaceMountain", name, config.getString("apiAccess"));
        BufferedImage buffImg =  convertToBufferedImage(resizeImage(img, 128, 128));
        Location frameLoc = new Location(Bukkit.getWorld(rideData.getString("world")), rideData.getDouble("frames." + frameNum.toString() + ".x"), rideData.getDouble("frames." + frameNum.toString() + ".y"), rideData.getDouble("frames." + frameNum.toString() + ".z"));
        makeMap.generatePhoto(frameLoc, buffImg);
        ChatUtil.sendPhotopassMessage(p, "Smile! Your Photo will be available at the exit!");
        frameNum++;
        if (frameNum > 11) {
            frameNum = 0;
        }
        if (uploadImgur) {
            try {
                ImgurUpload upload = new ImgurUpload();
                String bin = upload.convertToBinary(img);
                String url = upload.uploadImage(config.getString("imgurKey"), bin);
                ArrayList<String> playerArr = new ArrayList<String>();
                players.forEach(user -> {
                    playerArr.add(user.getUniqueId().toString());
                });
                String info = "Space Mountain on " + Core.getInstanceName() + "! Date of Photo: " + DateFormat.formattedTime();
                MongoManager mm = new MongoManager();
                mm.createPhoto(url, playerArr, info);
            }
            catch (Exception e) {
                e.printStackTrace();
                Core.logInfo("[PhotoPass] Error Uploading SpaceMountain Picture: " + e.getLocalizedMessage());
            }
        }
    }
}
