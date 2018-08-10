package gui.panel;

import gui.listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends WorkingPanel {

    public static ConfigPanel instance = new ConfigPanel();

    public JLabel lBudget = new JLabel("This Month's Budget($)");
    public JTextField vBudget = new JTextField();
    public JLabel lpath = new JLabel("Mysql Installation path");
    public JTextField vpath = new JTextField();
    public JButton b = new JButton("Update");

    public ConfigPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, b);
        this.setLayout(new BorderLayout());

        JPanel input = new JPanel();
        input.setLayout(new GridBagLayout());
        input.add(lBudget, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 10, 10));
        input.add(vBudget, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 10, 10));
        input.add(lpath, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 10, 10));
        input.add(vpath, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 10, 10));

        JPanel submit = new JPanel();
        submit.setLayout(new FlowLayout());
        submit.add(b);

        this.add(input, BorderLayout.CENTER);
        this.add(submit, BorderLayout.SOUTH);
        addListener();
        updateData();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(instance);
    }

    @Override
    public void updateData() {
        ConfigService configService = new ConfigService();
        String bud = configService.get(ConfigService.budget);
        String pat = configService.get(ConfigService.mysqlpath);
        if (bud != null) {
            vBudget.setText(bud);
        }
        if (pat != null) {
            vpath.setText(pat);
        }
        vBudget.grabFocus();
    }

    @Override
    public void addListener() {
        b.addActionListener(new ConfigListener());
    }
}
