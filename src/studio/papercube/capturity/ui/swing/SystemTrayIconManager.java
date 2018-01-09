package studio.papercube.capturity.ui.swing;

import studio.papercube.capturity.settings.SettingItem;
import studio.papercube.capturity.settings.Settings;
import studio.papercube.capturity.res.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.util.Objects;

import static java.lang.Boolean.parseBoolean;

public class SystemTrayIconManager {
    private static SystemTray st = SystemTray.getSystemTray();
    private static TrayIcon ic;

    public static void initMainIcon(JFrame window) {
        ic = new TrayIcon(Resources.instance.getIcon(), "Capturity");
        ;
        if (!SystemTray.isSupported()) return;
        ic.setImageAutoSize(true);
        ic.addActionListener(e -> window.setVisible(true));
        try {
            SystemTrayIconManager.getSystemTray().add(ic);
        } catch (AWTException e1) {
            ErrorNotifier.show(null, ErrorNotifier.toStackInfo(e1));
        }

        if (!parseBoolean(Settings.get(SettingItem.NotificationSilent)))
            ic.displayMessage("Capturity", "Capturity will be running in background until you click Quit", MessageType.INFO);
    }

    public static SystemTray getSystemTray() {
        return Objects.requireNonNull(st);
    }

    public static TrayIcon getTrayIcon() {
        return ic;
    }
}
