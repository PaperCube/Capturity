package studio.papercube.capturity.services;

import studio.papercube.capturity.capture.Capture;
import studio.papercube.capturity.capture.GroupImageWriter;
import studio.papercube.capturity.capture.RenderedImageWriter;
import studio.papercube.capturity.exceptionhandling.CatchAllThread;
import studio.papercube.capturity.nativesupport.Mouse;

import java.io.File;

import static studio.papercube.capturity.capture.RenderedImageWriter.nextImageFile;

/**
 * Created by imzhy on 2016/8/3.
 */
public class AutoCaptureService {
    private final Object lock = new Object();
    private boolean running = true;
    private File destinationDirectory;
    private int interval, pauseThreshold = -1;
    private double saveRatio = 1.0;

    private boolean isWaitingForNotification = false;
    Thread t;

    public AutoCaptureService() {
        t = new CatchAllThread(this::loop, getClass().getSimpleName());
//        t.setDaemon(true);
        Mouse.setOnMove(this::onMove);
    }

    private void loop() {
        for (; ; ) {
            if (!running) return;

            try {
                Capture cap = new Capture();
                RenderedImageWriter writer = new GroupImageWriter(nextImageFile(destinationDirectory));
                if (Math.random() <= saveRatio) cap.save(writer);

//                System.out.println(Mouse.location());
                if (Mouse.hasNotMovedForMillis(pauseThreshold)) waitForNotification(lock);
                else waitForMillis(lock, interval);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setPath(String path) {
        setDestinationDirectory(new File(path));
    }

    public String getPath() {
        return getDestinationDirectory().getAbsolutePath();
    }

    public void setDestinationDirectory(File destinationDirectory) {
        this.destinationDirectory = destinationDirectory;
    }

    public File getDestinationDirectory() {
        return this.destinationDirectory;
    }


    public void setPauseThreshold(int pauseThreshold) {
        this.pauseThreshold = pauseThreshold;
    }

    public int getPauseThreshold() {
        return this.pauseThreshold;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getInterval() {
        return this.interval;
    }

    public void start() {
        t.start();
    }

    public void stop() {
        running = false;
    }

    public void onMove() {
        if (isWaitingForNotification) synchronized (lock) {
            isWaitingForNotification = false;
            lock.notify();
        }
    }

    private void waitForNotification(Object lock) throws InterruptedException {
        synchronized (lock) {
            isWaitingForNotification = true;
            lock.wait();
        }
    }

    private void waitForMillis(Object lock, long millis) throws InterruptedException {
        synchronized (lock) {
            lock.wait(millis);
        }
    }
}
