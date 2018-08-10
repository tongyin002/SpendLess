package gui.panel;

import gui.listener.RecoverListener;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

public class RecoverPanel extends WorkingPanel {
    public static RecoverPanel instance = new RecoverPanel();
    public JButton b = new JButton("Recover");

    public RecoverPanel(){
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
        b.addActionListener(new RecoverListener());
    }
}
