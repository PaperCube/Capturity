package capturity.capture;

import studio.papercube.capturity.exceptionhandling.ThreadExceptionCatcher;

import java.awt.*;
import java.util.TimerTask;

import static capturity.capture.CaptureTaker.createScreenShot;

/**
 * Created by imzhy on 2016/4/10.
 */
@Deprecated
public class TakeScreenshot extends TimerTask {
    Robot r;
    String outputPath;

    public TakeScreenshot(Robot r, String outputPath) {
        this.r=r;
        this.outputPath = outputPath;
    }
    @Override
    public void run() {
        ThreadExceptionCatcher.register(Thread.currentThread());
        createScreenShot(r, outputPath);
    }
}
