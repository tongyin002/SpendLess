package gui.panel;

import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CateTableModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends WorkingPanel {

    public static CategoryPanel instance = new CategoryPanel();

    public JButton bAdd = new JButton("Add New");
    public JButton bDelete = new JButton("Delete");
    public JButton bEdit = new JButton("Edit");
    String[] columnNames = new String[]{"Category", "Times"};

    public CateTableModel tableModel = new CateTableModel();
    public JTable t = new JTable(tableModel);

    public CategoryPanel(){
        GUIUtil.setColor(ColorUtil.blueColor, bAdd, bDelete, bEdit);
        this.setLayout(new BorderLayout());

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(bAdd);
        buttons.add(bDelete);
        buttons.add(bEdit);

        JScrollPane sp = new JScrollPane(t);

        this.add(sp, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        addListener();
        updateData();
    }

    public Category getSelectedCategory(){

        int index = t.getSelectedRow();
        return tableModel.cs.get(index);
    }

    public void updateData(){
        tableModel.cs = new CategoryService().list();
        t.updateUI();
        t.getSelectionModel().setSelectionInterval(0, 0);
        if (tableModel.cs.size() == 0) {
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }else{
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }

    public void addListener(){
        CategoryListener cl = new CategoryListener();
        bDelete.addActionListener(cl);
        bEdit.addActionListener(cl);
        bAdd.addActionListener(cl);
    }
    public static void main(String[] args){
        GUIUtil.showPanel(instance);
    }
}
