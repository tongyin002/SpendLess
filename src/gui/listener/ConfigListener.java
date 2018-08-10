package gui.listener;

import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ConfigListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel p = ConfigPanel.instance;
        if (!GUIUtil.checkInteger(p.vBudget, "Budget This Month")){
            return;
        }

        String mysqlpath = p.vpath.getText();
        File f = new File(mysqlpath, "bin/mysql");
        if (!f.exists()) {
            JOptionPane.showMessageDialog(p, "Wrong Path for Mysql.");
            p.vpath.grabFocus();
            return;
        }
        ConfigService configService = new ConfigService();
        configService.update(ConfigService.budget, p.vBudget.getText());
        configService.update(ConfigService.mysqlpath, mysqlpath);

        JOptionPane.showMessageDialog(p, "Successfully Configured!");
    }
}
