package gui.panel;

import gui.listener.BackupListener;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

public class BackupPanel extends WorkingPanel {

    public static BackupPanel instance = new BackupPanel();
    public JButton b = new JButton("Back Up");

    public BackupPanel(){
        GUIUtil.setColor(ColorUtil.blueColor, b);
        this.add(b);
        addListener();
    }

    public static void main(String[] args){
        GUIUtil.showPanel(instance);
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        b.addActionListener(new BackupListener());
    }
}
