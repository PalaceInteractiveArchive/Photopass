package network.palace.photopass.handlers;

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

import static network.palace.photopass.utils.ImageUtils.convertToBufferedImage;
import static network.palace.photopass.utils.ImageUtils.resizeImage;

public class LoadEmpties {
    Photopass instance = Photopass.getPlugin(network.palace.photopass.Photopass.class);
    FileConfiguration config = instance.getConfig();
    File buzzConfigFile = new File(instance.getDataFolder(), File.separator + "rides/buzz.yml");
    FileConfiguration buzzData = YamlConfiguration.loadConfiguration(buzzConfigFile);
    File smConfigFile = new File(instance.getDataFolder(), File.separator + "rides/sm1.yml");
    FileConfiguration smData = YamlConfiguration.loadConfiguration(smConfigFile);
    File sm2ConfigFile = new File(instance.getDataFolder(), File.separator + "rides/sm2.yml");
    FileConfiguration sm2Data = YamlConfiguration.loadConfiguration(sm2ConfigFile);
    File rrcConfigFile = new File(instance.getDataFolder(), File.separator + "rides/rrc.yml");
    FileConfiguration rrcData = YamlConfiguration.loadConfiguration(rrcConfigFile);
    File hmConfigFile = new File(instance.getDataFolder(), File.separator + "rides/hm.yml");
    FileConfiguration hmData = YamlConfiguration.loadConfiguration(hmConfigFile);

    public void createBuzzEmpties() {
        MapRender makeMap = new MapRender();
        Image img = makeMap.getEmptyImageFromAPI("BuzzLightyear", config.getString("apiAccess"));
        BufferedImage buffImg =  convertToBufferedImage(resizeImage(img, 128, 128));
        for (int i = 0; i < 6; i++) {
            Location frameLoc = new Location(Bukkit.getWorld(buzzData.getString("world")), buzzData.getDouble("frames." + i + ".x"), buzzData.getDouble("frames." + i + ".y"), buzzData.getDouble("frames." + i + ".z"));
            makeMap.generatePhoto(frameLoc, buffImg);
        }
    }

    public void createSpaceMountain1Empties() {
        MapRender makeMap = new MapRender();
        Image img = makeMap.getEmptyImageFromAPI("SpaceMountain", config.getString("apiAccess"));
        BufferedImage buffImg =  convertToBufferedImage(resizeImage(img, 128, 128));
        for (int i = 0; i < 4; i++) {
            Location frameLoc = new Location(Bukkit.getWorld(smData.getString("world")), smData.getDouble("frames." + i + ".x"), smData.getDouble("frames." + i + ".y"), smData.getDouble("frames." + i + ".z"));
            makeMap.generatePhoto(frameLoc, buffImg);
        }
    }

    public void createSpaceMountain2Empties() {
        MapRender makeMap = new MapRender();
        Image img = makeMap.getEmptyImageFromAPI("SpaceMountain", config.getString("apiAccess"));
        BufferedImage buffImg =  convertToBufferedImage(resizeImage(img, 128, 128));
        for (int i = 0; i < 4; i++) {
            Location frameLoc = new Location(Bukkit.getWorld(sm2Data.getString("world")), sm2Data.getDouble("frames." + i + ".x"), sm2Data.getDouble("frames." + i + ".y"), sm2Data.getDouble("frames." + i + ".z"));
            makeMap.generatePhoto(frameLoc, buffImg);
        }
    }

    public void createRRCEmpties() {
        MapRender makeMap = new MapRender();
        Image img = makeMap.getEmptyImageFromAPI("RockNRoller", config.getString("apiAccess"));
        BufferedImage buffImg =  convertToBufferedImage(resizeImage(img, 128, 128));
        for (int i = 0; i < 5; i++) {
            Location frameLoc = new Location(Bukkit.getWorld(rrcData.getString("world")), rrcData.getDouble("frames." + i + ".x"), rrcData.getDouble("frames." + i + ".y"), rrcData.getDouble("frames." + i + ".z"));
            makeMap.generatePhoto(frameLoc, buffImg);
        }
    }

    public void createHMEmpties() {
        MapRender makeMap = new MapRender();
        Image img = makeMap.getEmptyImageFromAPI("HauntedMansion", config.getString("apiAccess"));
        BufferedImage buffImg =  convertToBufferedImage(resizeImage(img, 128, 128));
        for (int i = 0; i < 6; i++) {
            Location frameLoc = new Location(Bukkit.getWorld(hmData.getString("world")), hmData.getDouble("frames." + i + ".x"), hmData.getDouble("frames." + i + ".y"), hmData.getDouble("frames." + i + ".z"));
            makeMap.generatePhoto(frameLoc, buffImg);
        }
    }
}
