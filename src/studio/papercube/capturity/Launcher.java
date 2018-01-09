package studio.papercube.capturity;

import capturity.capture.CaptureSaver;
import studio.papercube.capturity.exceptionhandling.CatchAllThread;
import studio.papercube.capturity.exceptionhandling.ThreadExceptionCatcher;
import studio.papercube.capturity.nativesupport.Environment;
import studio.papercube.capturity.nativesupport.Mouse;
import studio.papercube.capturity.ui.swing.UserInterfaceMain;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Launcher {
    public static UserInterfaceMain mainwindow;
    public static int VERSION_CODE = 104;
    public static String VERSION = "v1.1.0-BETA-20161119";

    public static void main(String[] args) {
        ThreadExceptionCatcher.register(Thread.currentThread());
        Environment.doUnitTest();
        SealedTool.UIStyleInit.init();
        String argsString = Stream.of(args)
                .collect(Collectors.joining(","));
        System.out.println(argsString);

        System.out.println(VERSION);
        System.out.println(SealedTool.DateFormatter.getCurrent());

//		new VerifyWindow();

        checkSingle(Launcher::exitWhenSingle);

        launchUIWithComment(args,String.format("Capturity %s - %s",VERSION, VERSION_CODE));

        Mouse.setDetectingEnabled(true);
    }

    private static void launchUIWithComment(String[] args, String comment) {
        SwingUtilities.invokeLater(() -> {
            mainwindow = new UserInterfaceMain(comment);
            for (String arg : args) {
                if (arg.toLowerCase().equals("silentlaunch")) {
                    return;
                }
            }
            mainwindow.setVisible(true);

        });
    }

    private static void exitWhenSingle(){
        JOptionPane.showMessageDialog(null, "你不可以同时运行两个程序");
        exit();
    }

    private static void checkSingle(Runnable r){
        try {
            if (!SingleInstanceChecker.isSingle()) {
                r.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void shutdownExecutorsAndAwaitForTermination(){
        CaptureSaver.getExecutor().shutdown();
        try {
            CaptureSaver.getExecutor().awaitTermination(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

    public static void exit() {
        new CatchAllThread(Launcher::shutdownExecutorsAndAwaitForTermination).start();
    }



}
