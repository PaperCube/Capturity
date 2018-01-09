package studio.papercube.capturity.capture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by imzhy on 2016/8/3.
 */
public class Capture {
    BufferedImage capture;
    private static Robot r;

    Capture(Rectangle rectangle) throws AWTException {

        if (r == null) r = new Robot();

        capture = r.createScreenCapture(rectangle);
    }

    public Capture() throws AWTException {
        this(getFullScreenRectangle());
    }

    static Rectangle getFullScreenRectangle() {
        Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) dem.getWidth();
        int y = (int) dem.getHeight();

        return new Rectangle(x, y);
    }


    public void save(RenderedImageWriter writer) throws IOException {
        writer.write(capture);
    }
}
