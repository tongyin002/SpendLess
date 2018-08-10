package gui.panel;

import entity.Record;
import service.RecordService;
import service.ReportService;
import util.ChartUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReportPanel extends WorkingPanel {
    public static ReportPanel instance = new ReportPanel();
    public JLabel l = new JLabel();

    public ReportPanel(){
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image i = ChartUtil.getImage(rs,500, 370);
        l.setIcon(new ImageIcon(i));
        this.add(l);
        addListener();
    }

    public static void main(String[] args){
        GUIUtil.showPanel(instance);
    }

    @Override
    public void updateData() {
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image i = ChartUtil.getImage(rs, 500, 370);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
    }

    @Override
    public void addListener() {

    }
}
