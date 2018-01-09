package capturity.capture;

import studio.papercube.capturity.exceptionhandling.ThreadExceptionCatcher;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Deprecated
public class CaptureSaver {
    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void submit(BufferedImage image, String absolutePath) {
        executor.execute(() -> save(image, absolutePath));
    }

    public static ExecutorService getExecutor() {
        return executor;
    }

    private static void save(BufferedImage image, String absolutePath) {
        ThreadExceptionCatcher.register(Thread.currentThread());

    }
}
