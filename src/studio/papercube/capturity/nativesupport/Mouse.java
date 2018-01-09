package studio.papercube.capturity.nativesupport;

import java.awt.*;

import static java.lang.System.currentTimeMillis;

/**
 * Created by imzhy on 2016/8/3.
 */
public class Mouse {

    private static long lastMove=currentTimeMillis();
    private static Point lastPoint=location();
    private static boolean isDetectingEnabled = false;
    private static Runnable onMoveMouse;
    public static Point location(){
        return MouseInfo.getPointerInfo().getLocation();
    }

    public static void setDetectingEnabled(boolean requiredEnable){
        if (isDetectingEnabled) {
            isDetectingEnabled=requiredEnable;
        }else{
            if(requiredEnable)new MouseDetector();
            isDetectingEnabled=requiredEnable;
        }
    }

    public static void setOnMove(Runnable runnable) {
        onMoveMouse=runnable;
    }

    public static boolean isDetectingEnabled() {
        return isDetectingEnabled;
    }

    public static boolean hasNotMovedForMillis(int millis) {
        return currentTimeMillis()-lastMove >= millis;
    }

    private static class MouseDetector{
        Thread t;
        MouseDetector(){
            t = new Thread(this::detectMouse, getClass().getSimpleName());
            t.setDaemon(true);
            t.start();
        }

        private void detectMouse(){
            mainLoop:
            for(;;) {
                if(!isDetectingEnabled) break mainLoop;

                Point currentPoint = location();

                //如果点发生变更
                if (!lastPoint.equals(currentPoint)) {
                    lastMove = currentTimeMillis();
                    lastPoint=currentPoint;
                    if(onMoveMouse!=null) onMoveMouse.run();
                }

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
