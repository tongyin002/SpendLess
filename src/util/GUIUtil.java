package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIUtil {

    private static String images = "/Users/tongyin/Public/SpendLess/images"; // image folder path

    // check if JTextField is empty
    public static boolean checkEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(null, input + " cannot be empty.");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    // check if the content of JTextField is integer
    public static boolean checkInteger(JTextField tf, String input) {
        String text = tf.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, input + " has to be an integer.");
            tf.grabFocus();
            return false;
        }
    }

    // check if the content of JTextField is not integer 0
    public static boolean checkNotZero(JTextField tf, String input) {
        if (!checkInteger(tf, input)) return false;

        int value = Integer.parseInt(tf.getText().trim());
        if (value == 0) {
            JOptionPane.showMessageDialog(null, input + " must not be 0.");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    // set color for components
    public static void setColor(Color color, JComponent... cs) {
        for (JComponent c : cs) {
            c.setForeground(color);
        }
    }

    // add image icon and pop-up text for a button
    public static void setImageIcon(JButton b, String fileName, String tipText) {
        ImageIcon icon = new ImageIcon(new File(images, fileName).getAbsolutePath());
        b.setIcon(icon);
        b.setPreferredSize(new Dimension(61, 81));
        b.setToolTipText(tipText);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tipText);
    }

    // set skin
    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showPanel(JPanel p) {
        showPanel(p,0.85);
    }

    public static void showPanel(JPanel p,double stretchRate) {
        useLNF();
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(stretchRate);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }
}
