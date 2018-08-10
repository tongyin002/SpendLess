package gui.listener;

import com.sun.tools.javac.Main;
import entity.Category;
import gui.panel.CategoryPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.SpendPanel;
import service.RecordService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel p = RecordPanel.instance;
        if (p.cmodel.cs.isEmpty()) {
            JOptionPane.showMessageDialog(p, "No Category Can be Chosen From. Add Category First!");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return;
        }

        if (!GUIUtil.checkNotZero(p.vSpend, "Spend")){
            return;
        }

        int spend = Integer.parseInt(p.vSpend.getText().trim());
        Category c = p.getSelectedCategory();
        String memo = p.vMemo.getText();
        Date date = p.datePicker.getDate();

        new RecordService().add(spend, c, memo, date);
        JOptionPane.showMessageDialog(p, "Successfully Added");
        MainPanel.instance.workingPanel.show(SpendPanel.instance);
    }
}
