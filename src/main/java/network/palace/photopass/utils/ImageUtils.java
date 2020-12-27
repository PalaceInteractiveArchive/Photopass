package network.palace.photopass.utils;


import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Tom
 * @since 27/12/2020
 * @version 1.0.0
 */

public class ImageUtils {
    public Image resizeImage(Image originalImage, int targetWidth, int targetHeight) throws Exception {
        return originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
    }

    public BufferedImage convertToBufferedImage(Image image)
    {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(null), image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }
}
