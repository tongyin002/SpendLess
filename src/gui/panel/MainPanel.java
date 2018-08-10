package gui.panel;

import gui.listener.ToolBarListener;
import util.CenterPanel;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public static MainPanel instance = new MainPanel();
    public JToolBar tb = new JToolBar();
    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();

    public CenterPanel workingPanel;

    public MainPanel() {
        GUIUtil.setImageIcon(bSpend, "home.png", "Expense Overview");
        GUIUtil.setImageIcon(bRecord, "record.png", "Take Notes");
        GUIUtil.setImageIcon(bCategory, "category2.png", "Expense Category");
        GUIUtil.setImageIcon(bReport, "report.png", "Monthly Statistics");
        GUIUtil.setImageIcon(bConfig, "config.png", "Settings");
        GUIUtil.setImageIcon(bBackup, "backup.png", "Back Up");
        GUIUtil.setImageIcon(bRecover, "restore.png", "Recover");

        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bCategory);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);

        workingPanel = new CenterPanel(0.85);

        this.setLayout(new BorderLayout());
        this.add(tb, BorderLayout.NORTH);
        this.add(workingPanel, BorderLayout.CENTER);
        addListener();
    }

    public void addListener(){
        ToolBarListener listener = new ToolBarListener();

        bBackup.addActionListener(listener);
        bCategory.addActionListener(listener);
        bConfig.addActionListener(listener);
        bRecord.addActionListener(listener);
        bRecover.addActionListener(listener);
        bReport.addActionListener(listener);
        bSpend.addActionListener(listener);
    }

    public static void main(String[] args){
        GUIUtil.showPanel(instance, 1);
    }
}
