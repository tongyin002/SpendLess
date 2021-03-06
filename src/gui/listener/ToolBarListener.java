package gui.listener;

import gui.panel.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        MainPanel p = MainPanel.instance;

        if (b == p.bSpend) {
            p.workingPanel.show(SpendPanel.instance);
        }else if (b == p.bRecord) {
            p.workingPanel.show(RecordPanel.instance);
        }else if (b == p.bCategory) {
            p.workingPanel.show(CategoryPanel.instance);
        }else if (b == p.bConfig) {
            p.workingPanel.show(ConfigPanel.instance);
        }else if (b == p.bReport) {
            p.workingPanel.show(ReportPanel.instance);
        }else if (b == p.bBackup) {
            p.workingPanel.show(BackupPanel.instance);
        }else if (b == p.bRecover) {
            p.workingPanel.show(RecoverPanel.instance);
        }
    }
}
