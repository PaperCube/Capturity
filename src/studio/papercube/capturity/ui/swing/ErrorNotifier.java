package studio.papercube.capturity.ui.swing;

import studio.papercube.capturity.res.Resources;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

public class ErrorNotifier extends JDialog {
    private static final long serialVersionUID = -1389333044922069275L;

    public ErrorNotifier(String simple, String complex) {
        setTitle("错误");
        setIconImage(Resources.instance.getIcon());
        setSize(512, 288);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel label = new JLabel("错误");
        panel.add(label);
        label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label.setForeground(Color.RED);

        JLabel label_1 = new JLabel(simple);
        panel.add(label_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportBorder(new TitledBorder(null, "\u8BE6\u7EC6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JTextPane textPane = new JTextPane();
        scrollPane.setViewportView(textPane);
        textPane.setEditable(true);
        textPane.setText(complex);
        panel.setAlignmentX(0.0f);
        setVisible(true);
    }

    public static String toStackInfo(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public static void show(String p1, String p2) {
        new ErrorNotifier(Objects.toString(p1), Objects.toString(p2));
    }
}
