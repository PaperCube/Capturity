package capturity.capture;

import java.awt.*;

import static java.lang.System.currentTimeMillis;

/**
 * Created by imzhy on 2016/4/10.
 */
//FIXME ..

@Deprecated
public class AutoSleepController extends Controller {
    final long maxVal;
    Point lastInfo = new Point(0, 0);
    long lastUpdatedTime = currentTimeMillis();

    protected AutoSleepController(long maxVal) {
        this.maxVal = maxVal;
    }

    @Override
    public void run() {
        Point mouseInfo = MouseInfo.getPointerInfo().getLocation();
        if (currentTimeMillis() - lastUpdatedTime > maxVal) lastUpdatedTime = currentTimeMillis();
    }


}
