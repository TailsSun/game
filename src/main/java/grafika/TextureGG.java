package grafika;

import utils.ImgLoader;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by DNS on 11.04.2017.
 */
public class TextureGG {
    BufferedImage img;

    public TextureGG(String imageName) {
        try {
            img = ImgLoader.loadImg(imageName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("странная хрень");
        }
    }

    public BufferedImage cut(int x, int y, int w, int h) {
        return img.getSubimage(x, y, w, h);
    }
}
