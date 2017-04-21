package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by DNS on 11.04.2017.
 */
public class ImgLoader {
    public static final String direktory = "resources/";
    public static BufferedImage loadImg(String fileName) throws IOException {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(direktory  + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
