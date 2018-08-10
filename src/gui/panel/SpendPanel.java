package gui.panel;

import entity.Spend;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class SpendPanel extends WorkingPanel {

    public static SpendPanel instance = new SpendPanel();

    public JLabel lMonth = new JLabel("Expense This Month");
    public JLabel lDay = new JLabel("Expense Today");
    public JLabel lDaily = new JLabel("Average Daily Expense");
    public JLabel lLeft = new JLabel("Monthly Budget Left");
    public JLabel lLeftDaily = new JLabel("Daily Budget Left");
    public JLabel lleftDays = new JLabel("Days Left This Month");

    public JLabel vMonth = new JLabel("$2000");
    public JLabel vDay = new JLabel("$20");
    public JLabel vDaily = new JLabel("$10");
    public JLabel vLeft = new JLabel("$1000");
    public JLabel vLeftDaily = new JLabel("$30");
    public JLabel vleftDays = new JLabel("15");

    CircleProgressBar cpb;

    public SpendPanel(){
        this.setLayout(new BorderLayout());
        cpb = new CircleProgressBar();
        cpb.setBackgroundColor(ColorUtil.blueColor);

        GUIUtil.setColor(ColorUtil.grayColor, lMonth, lDay, lDaily, lLeft, lLeftDaily, lleftDays,
                            vDaily, vLeft, vLeftDaily, vleftDays);
        GUIUtil.setColor(ColorUtil.blueColor, vMonth, vDay);

        vMonth.setFont(new Font("TimesRoman", Font.BOLD, 23));
        vDay.setFont(new Font("TimesRoman", Font.BOLD, 23));
        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);
        updateData();
    }

    private JPanel center(){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(west(), BorderLayout.WEST);
        p.add(center2(), BorderLayout.CENTER);
        return p;
    }

    private Component west(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 1));
        p.add(lMonth);
        p.add(vMonth);
        p.add(lDay);
        p.add(vDay);
        return p;
    }

    private Component center2(){
        return cpb;
    }

    private JPanel south() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 4));
        p.add(lDaily);
        p.add(lLeft);
        p.add(lLeftDaily);
        p.add(lleftDays);
        p.add(vDaily);
        p.add(vLeft);
        p.add(vLeftDaily);
        p.add(vleftDays);
        return p;
    }

    public static void main(String[] args){
        GUIUtil.showPanel(instance);
    }

    @Override
    public void updateData() {
        Spend spend = new SpendService().getSpend();
        vMonth.setText(spend.monthSpend);
        vDay.setText(spend.todaySpend);
        vDaily.setText(spend.dailySpend);
        vLeft.setText(spend.monthlyLeft);
        vLeftDaily.setText(spend.dailyLeft);
        vleftDays.setText(spend.daysLeft);

        cpb.setProgress(spend.usagePercent);
        if (spend.runOut) {
            vLeft.setForeground(ColorUtil.warningColor);
            vMonth.setForeground(ColorUtil.warningColor);
            vDay.setForeground(ColorUtil.warningColor);
        }else{
            vLeft.setForeground(ColorUtil.grayColor);
            vMonth.setForeground(ColorUtil.blueColor);
            vDay.setForeground(ColorUtil.blueColor);
        }
        cpb.setForegroundColor(ColorUtil.getByPercent(spend.usagePercent));
        addListener();
    }

    @Override
    public void addListener() {

    }
}
