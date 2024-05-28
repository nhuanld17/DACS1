package GUI;

//import javax.swing.*;
//import java.awt.*;
//
//public class RoundedPanel extends JPanel {
//    private int borderRadius;
//
//    public RoundedPanel(int borderRadius) {
//        this.borderRadius = borderRadius;
//        setOpaque(false); // Làm cho nền của JPanel trong suốt
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g.create();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setColor(getBackground());
//        g2.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
//        g2.dispose();
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(300, 200);
//        frame.setLayout(null);
//
//        RoundedPanel panel_1 = new RoundedPanel(20);
//        panel_1.setBounds(10, 11, 233, 149);
//        panel_1.setBackground(Color.BLUE);
//
//        frame.add(panel_1);
//        frame.setVisible(true);
//    }
//}

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int borderRadius;
    private int shadowSize;

    public RoundedPanel(int borderRadius, int shadowSize) {
        this.borderRadius = borderRadius;
        this.shadowSize = shadowSize;
        setOpaque(false); // Làm cho nền của JPanel trong suốt
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ bóng
        g2.setColor(Color.GRAY);
        g2.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize, getHeight() - shadowSize, borderRadius, borderRadius);

        // Vẽ nền của panel
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - shadowSize, getHeight() - shadowSize, borderRadius, borderRadius);
        
        g2.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(null);

        RoundedPanel panel_1 = new RoundedPanel(150,10);
        panel_1.setBounds(10, 11, 150, 150);
        panel_1.setBackground(Color.BLUE);

        frame.add(panel_1);
        frame.setVisible(true);
    }
}

