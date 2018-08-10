package gui.panel;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CateComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class RecordPanel extends WorkingPanel {

    public static RecordPanel instance = new RecordPanel();

    public JLabel lSpend = new JLabel("Spent($)");
    public JLabel lCate = new JLabel("Category");
    public JLabel lmemo = new JLabel("Memo");
    public JLabel lDate = new JLabel("Date");

    public JTextField vSpend = new JTextField();
    public CateComboBoxModel cmodel = new CateComboBoxModel();
    public JComboBox<Category> vCate = new JComboBox<>(cmodel);
    public JTextField vMemo = new JTextField();
    public JXDatePicker datePicker = new JXDatePicker(new Date());

    public JButton b = new JButton("Take a Note");

    public RecordPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lSpend, lCate, lmemo, lDate);
        GUIUtil.setColor(ColorUtil.blueColor, b);
        this.setLayout(new BorderLayout());
        JPanel pInput = new JPanel();
        pInput.setLayout(new GridBagLayout());
        pInput.add(lSpend, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 10, 10));
        pInput.add(vSpend, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 10, 10));
        pInput.add(lCate, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 10, 10));
        pInput.add(vCate, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 10, 10));
        pInput.add(lmemo, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 10, 10));
        pInput.add(vMemo, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 10, 10));
        pInput.add(lDate, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 10, 10));
        pInput.add(datePicker, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 10, 10));


        JPanel pSubmit = new JPanel();
        pSubmit.setLayout(new FlowLayout());
        pSubmit.add(b);

        this.add(pInput, BorderLayout.CENTER);
        this.add(pSubmit, BorderLayout.SOUTH);

        changeSize(vCate, vMemo, vSpend, datePicker);
        addListener();
        updateData();
    }

    public static void changeSize(Component... cs){
        for (Component c : cs) {
            c.setPreferredSize(new Dimension(c.getPreferredSize().width, c.getPreferredSize().height / 2));
        }
    }

    public Category getSelectedCategory(){
        return (Category) vCate.getSelectedItem();
    }
    @Override
    public void updateData() {
        cmodel.cs = new CategoryService().list();
        vCate.updateUI();
        resetInput();
        vSpend.grabFocus();
    }

    private void resetInput(){
        vSpend.setText("0");
        if (!cmodel.cs.isEmpty()){
            vCate.setSelectedIndex(0);
        }
        vMemo.setText("");
        datePicker.setDate(new Date());
    }

    @Override
    public void addListener() {
        b.addActionListener(new RecordListener());
    }

    public static void main(String[] args){
        GUIUtil.showPanel(instance);
    }
}
