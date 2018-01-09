package studio.papercube.capturity;

import studio.papercube.capturity.nativesupport.Mouse;
import studio.papercube.capturity.services.AutoCaptureService;

/**
 * Created by imzhy on 2016/8/10.
 */
public class Debuggable {
    public static void main(String[] args) {
        Mouse.setDetectingEnabled(true);
        AutoCaptureService service = new AutoCaptureService();
        service.setInterval(1000);
        service.setPath(null);
        service.setPauseThreshold(5000);
        service.start();
    }
}
