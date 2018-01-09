package studio.papercube.capturity.ui.swing;

import studio.papercube.capturity.exceptionhandling.ThreadExceptionCatcher;
import studio.papercube.capturity.services.AutoCaptureService;
import studio.papercube.capturity.settings.SettingItem;
import studio.papercube.capturity.settings.Settings;
import studio.papercube.capturity.ui.swing.CapUnits.CaptureIntervalUnit;
import studio.papercube.capturity.ui.swing.CapUnits.CaptureAutoSleepUnit;
import studio.papercube.capturity.Launcher;
import studio.papercube.capturity.res.Resources;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import static java.lang.Boolean.parseBoolean;

public class UserInterfaceMain extends JFrame implements ActionListener {
    AutoCaptureService captureService;
    private JTextField tfPath;
    private JTextField tfCaptureInterval;
    private JTextField tfAutoSleepThreshold;
    private JComboBox cbCaptureInterval;
    private JCheckBox chbSilent;
    private JCheckBox chbNotifyForEveryCapture;
    private JButton btnConfigAdvanced;
    private JButton btnStart;
    private JTextField tfOnlySave;
    private JCheckBox chbOnlySave;

    public UserInterfaceMain(String comment) {
        super(comment);
        ThreadExceptionCatcher.register(Thread.currentThread());
        setSize(640, 360);

        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 614, 29);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("选择输出路径");
        label.setBounds(9, 9, 72, 15);
        panel.add(label);

        tfPath = new JTextField();
        tfPath.setBounds(86, 6, 456, 21);
        panel.add(tfPath);
        tfPath.setColumns(75);

        JButton btnBrowse = new JButton("浏览");
        btnBrowse.setBounds(547, 5, 57, 23);
        panel.add(btnBrowse);

        JPanel panel_1 = new JPanel();
        panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_1.setBounds(10, 37, 614, 29);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel label_1 = new JLabel("平均");
        label_1.setBounds(10, 10, 24, 15);
        panel_1.add(label_1);

        tfCaptureInterval = new JTextField();
        tfCaptureInterval.setBounds(44, 7, 66, 21);
        panel_1.add(tfCaptureInterval);
        tfCaptureInterval.setColumns(10);

        cbCaptureInterval = new JComboBox();
        cbCaptureInterval.setEnabled(false);
        cbCaptureInterval.setModel(new DefaultComboBoxModel(CaptureIntervalUnit.values()));
        cbCaptureInterval.setBounds(120, 7, 49, 21);
        panel_1.add(cbCaptureInterval);

        JLabel label_2 = new JLabel("一张，自动休眠");
        label_2.setBounds(179, 10, 94, 15);
        panel_1.add(label_2);

        tfAutoSleepThreshold = new JTextField();
        tfAutoSleepThreshold.setEnabled(true);
        tfAutoSleepThreshold.setBounds(272, 4, 66, 21);
        panel_1.add(tfAutoSleepThreshold);
        tfAutoSleepThreshold.setColumns(10);

        JComboBox cbAutoSleepThreshold = new JComboBox();
        cbAutoSleepThreshold.setEnabled(false);
        cbAutoSleepThreshold.setModel(new DefaultComboBoxModel(CaptureAutoSleepUnit.values()));
        cbAutoSleepThreshold.setBounds(348, 4, 83, 21);
        panel_1.add(cbAutoSleepThreshold);

        tfOnlySave = new JTextField();
        tfOnlySave.setBounds(505, 7, 66, 21);
        panel_1.add(tfOnlySave);
        tfOnlySave.setColumns(10);

        chbOnlySave = new JCheckBox("只保留");
        chbOnlySave.setActionCommand("saveOnly");
        chbOnlySave.setBounds(437, 6, 66, 23);
        panel_1.add(chbOnlySave);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 76, 614, 7);
        getContentPane().add(separator);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(10, 76, 614, 62);
        getContentPane().add(panel_2);
        panel_2.setLayout(null);

        chbSilent = new JCheckBox("静默");
        chbSilent.setBounds(6, 6, 103, 23);
        panel_2.add(chbSilent);

        JCheckBox chbNotifyWhenErr = new JCheckBox("错误时推送通知");
        chbNotifyWhenErr.setEnabled(false);
        chbNotifyWhenErr.setSelected(true);
        chbNotifyWhenErr.setBounds(6, 31, 158, 23);
        panel_2.add(chbNotifyWhenErr);

        chbNotifyForEveryCapture = new JCheckBox("为每张捕获推送通知");
        chbNotifyForEveryCapture.setEnabled(false);
        chbNotifyForEveryCapture.setBounds(320, 6, 288, 23);
        panel_2.add(chbNotifyForEveryCapture);

        btnConfigAdvanced = new JButton("配置高级设置");
        btnConfigAdvanced.setEnabled(false);
        btnConfigAdvanced.setBounds(320, 31, 288, 23);
        panel_2.add(btnConfigAdvanced);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(10, 251, 614, 70);
        getContentPane().add(panel_3);
        panel_3.setLayout(null);

        btnStart = new JButton("开始捕获");

        btnStart.setBounds(10, 10, 594, 23);
        panel_3.add(btnStart);

        JButton btnQuit = new JButton("Quit");
        btnQuit.setBounds(511, 43, 93, 23);
        panel_3.add(btnQuit);

        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(null, "\u72B6\u6001", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_4.setBounds(10, 148, 614, 93);
        getContentPane().add(panel_4);
        panel_4.setLayout(null);

        JLabel lblNotAvailable = new JLabel("Not Available");
        lblNotAvailable.setBounds(23, 23, 118, 15);
        panel_4.add(lblNotAvailable);

        setIconImage(Resources.instance.getIcon());

        readSettings();
        updateWidgetAbility();
//		setVisible(true);

        {
            btnBrowse.addActionListener(e -> {
                JFileChooser fd = new JFileChooser();
                fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fd.showSaveDialog(this);
                fd.setVisible(true);
                tfPath.setText(fd.getSelectedFile().getAbsolutePath());
            });

            btnQuit.addActionListener(e -> {
                saveSettings();
                Launcher.exit();
            });

            btnStart.setActionCommand("start");
            btnStart.addActionListener(this);
        }

        SystemTrayIconManager.initMainIcon(this);
    }

    private void readSettings() {
        tfPath.setText(Settings.get(SettingItem.OutputPath));
        tfCaptureInterval.setText(Settings.get(SettingItem.Interval));
        chbSilent.setSelected(parseBoolean(Settings.get(SettingItem.NotificationSilent)));
        tfAutoSleepThreshold.setText(Settings.get(SettingItem.autoSleepThreshold));
    }

    private void saveSettings() {
        Settings.set(SettingItem.OutputPath, tfPath.getText());
        Settings.set(SettingItem.Interval, tfCaptureInterval.getText());
        Settings.set(SettingItem.NotificationSilent, chbSilent.isSelected());
        Settings.set(SettingItem.autoSleepThreshold,tfAutoSleepThreshold.getText());
    }

    private int toMillis(String seconds) {
        return (int)(Double.parseDouble(seconds)*1000);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        saveSettings();
        switch (e.getActionCommand()) {
            case "start":
                onStart(btnStart);
                break;
            case "finish":
                onFinish(btnStart);
                break;
            case "saveOnly":
                updateWidgetAbility();
        }
    }

	
	
	/*
	 * =========================Event Handle===================
	 */


    public void onStart(JButton b) {
        captureService=new AutoCaptureService();
        captureService.setInterval(toMillis(tfCaptureInterval.getText()));
        captureService.setPath(tfPath.getText());
        captureService.setPauseThreshold(toMillis(tfAutoSleepThreshold.getText()));
        captureService.start();
        b.setText("停止");
        b.setActionCommand("finish");
    }

    public void onFinish(JButton b) {
        if (captureService != null) captureService.stop();
        b.setText("开始捕获");
        b.setActionCommand("start");
    }


    public void updateWidgetAbility() {
        tfOnlySave.setEnabled(chbOnlySave.isSelected());
    }
}


