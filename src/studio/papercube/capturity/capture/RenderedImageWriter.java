package studio.papercube.capturity.capture;

import studio.papercube.capturity.SealedTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by imzhy on 2016/8/3.
 */
abstract public class RenderedImageWriter {
    private File target;
    public RenderedImageWriter(File file) {
        target=file;
    }

    void write(RenderedImage image) throws IOException {
        ImageIO.write(image, "png", target);
    }

    public static File nextImageFile(String parentFolder){
        String absolutePath = String.format("%s\\Capturity Screenshot %s.png", parentFolder, SealedTool.DateFormatter.getCurrent());
        return new File(absolutePath);
    }

    public static File nextImageFile(File parentDirectory) {
        return new File(parentDirectory, String.format("Capturity Screenshot %s.png", SealedTool.DateFormatter.getCurrent()));
    }
}
