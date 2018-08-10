package gui.listener;

import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import gui.panel.RecoverPanel;
import service.ConfigService;
import util.MysqlUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecoverListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecoverPanel p = RecoverPanel.instance;
        String mysqlpath = new ConfigService().get(ConfigService.mysqlpath);
        if(mysqlpath.isEmpty()){
            JOptionPane.showMessageDialog(p, "Please specify Mysql installation path before backup");
            MainPanel.instance.workingPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.vpath.grabFocus();
            return;
        }

        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("spendless.sql"));
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".sql");
            }

            @Override
            public String getDescription() {
                return ".sql";
            }
        });

        int returnVal =  fc.showSaveDialog(p);
        File file = fc.getSelectedFile();
        System.out.println(file);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                MysqlUtil.recover(mysqlpath,file.getAbsolutePath());
                JOptionPane.showMessageDialog(p, "Recover Success");
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(p, "Recover failed\r\n,Error Message:\r\n"+e1.getMessage());
            }

        }
    }
}
