package studio.papercube.capturity.capture;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by imzhy on 2016/8/3.
 */
public class GroupImageWriter extends RenderedImageWriter {
    public static final Executor executor = Executors.newCachedThreadPool();

    /**
     * 储存了在写过程中发生的IOException，目前没有用处。
     */
    private Queue<Throwable> exceptions = new PriorityQueue<>();
    public GroupImageWriter(File file) {
        super(file);
    }

    @Override
    void write(RenderedImage image) throws IOException {
        executor.execute(() -> {
            try {
                super.write(image);
            } catch (Exception e) {
                exceptions.offer(e);
            }
        });
    }

}
