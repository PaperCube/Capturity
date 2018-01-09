package capturity.capture;

import studio.papercube.capturity.SealedTool;
import studio.papercube.capturity.ui.swing.ErrorNotifier;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@Deprecated
public class CaptureTaker{
    public int capturesTaken = 0;
    private int delay = 0;
    private double interval = 0;
    private String outputPath;
    private Robot r;
    private Controller shotController;

    public CaptureTaker() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            JOptionPane.showMessageDialog(null, "无法创建Robot实例\n" + ErrorNotifier.toStackInfo(e), "加载屏幕截图捕捉器时出现错误", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void createScreenShot(Robot r, String path) {
        Dimension dem = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) dem.getWidth();
        int y = (int) dem.getHeight();
        System.out.println("Creating Screenshot");
        String absolutePath = String.format("%s\\Capturity Screenshot %s.png", path, SealedTool.DateFormatter.getCurrent());
        BufferedImage image = r.createScreenCapture(new Rectangle(x, y));
        CaptureSaver.submit(image, absolutePath);
    }

    public void start(){
        if(shotController !=null) shotController.cancel();
        //FIXME
//        shotController = new AutoSleepController(r, outputPath);
    }

    public void cancel(){
        if(shotController !=null) shotController.cancel();
    }

    //TODO TO BE IMPLEMENTED
    public void setDelay(int delay) {
        this.delay = delay * 1000;
    }

    public void setInterval(double interval) {
        this.interval = interval * 1000;
    }

    //TODO 获得上级并且链接到用户界面
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }


}
