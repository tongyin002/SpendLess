package gui.listener;

import gui.panel.BackupPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import service.ConfigService;
import util.MysqlUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class BackupListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel p = BackupPanel.instance;
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
            //如果保存的文件名没有以.sql结尾，自动加上.sql
            System.out.println(file);
            if(!file.getName().toLowerCase().endsWith(".sql"))
                file = new File(file.getParent(),file.getName()+".sql");
            System.out.println(file);

            try {
                MysqlUtil.backup(mysqlpath, file.getAbsolutePath());
                JOptionPane.showMessageDialog(p, "Backup Success,the backup file is located in:\r\n"+file.getAbsolutePath());
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(p, "Backup failed, \r\n,Error Message:\r\n"+e1.getMessage());
            }

        }
    }
}
