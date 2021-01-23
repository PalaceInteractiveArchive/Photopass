package network.palace.photopass.renderer;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import network.palace.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class MapRender
        implements Listener
{
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private List<ItemFrame> frames = new ArrayList();
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private List<CustomMapRenderer> renderers = new ArrayList();

    public Image getImageFromAPI(String rideName, String pString, String accessToken)
    {
        try
        {
            if (pString == "") {
                return null;
            }
            return ImageIO.read(new URL("https://internal-api.palace.network/minecraft/ridephoto/create/" + rideName + "/" + pString + "?access=" + accessToken));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Image getEmptyImageFromAPI(String rideName,String accessToken)
    {
        try
        {
            return ImageIO.read(new URL("https://internal-api.palace.network/minecraft/ridephoto/createEmpty/" + rideName + "?access=" + accessToken));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public void generatePhoto(Location location, BufferedImage image)
    {
        MapView map = Bukkit.createMap(location.getWorld());
        for (MapRenderer render : map.getRenderers()) {
            map.removeRenderer(render);
        }
        map.addRenderer(new CustomMapRenderer(image));

        ItemStack stack = new ItemStack(Material.MAP, 1, map.getId());
        ItemMeta meta = stack.getItemMeta();

        stack.setItemMeta(meta);
        ItemFrame frame = getFrame(location);
        if (frame != null) {
            frame.setItem(stack);
        }
        this.frames.add(frame);
    }


    private ItemFrame getFrame(Location loc)
    {
        try {
            Entity[] arrayOfEntity;
            int j = (arrayOfEntity = loc.getChunk().getEntities()).length;
            for (int i = 0; i < j; i++)
            {
                Entity e = arrayOfEntity[i];
                if (((e instanceof ItemFrame)) &&
                        (e.getLocation().getBlock().getLocation().distance(loc) <= 0.5D)) {
                    return (ItemFrame)e;
                }
            }
            return null;
        } catch (Exception e) {
            Core.logInfo("[Photo Pass] Error Locating Frame! Check config!");
            return null;
        }
    }



    private class CustomMapRenderer
            extends MapRenderer
    {
        private BufferedImage image;
        @SuppressWarnings({ "rawtypes", "unchecked" })
        private List<String> rendered = new ArrayList();

        public CustomMapRenderer(BufferedImage image)
        {
            MapRender.this.renderers.add(this);
            this.image = image;
        }

        public void render(MapView view, MapCanvas canvas, Player p)
        {
            if (this.rendered.contains(p.getName())) {
                return;
            }
            this.rendered.add(p.getName());
            view.setScale(MapView.Scale.FARTHEST);
            canvas.drawImage(0, 0, this.image);
        }
    }
}