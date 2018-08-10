package gui.listener;

import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPanel p = CategoryPanel.instance;
        JButton b = (JButton) e.getSource();

        if (b == p.bAdd) {
            String name = JOptionPane.showInputDialog(null);
            if (!judge(name, p)){
                return;
            }
            new CategoryService().add(name);
        }else if (b == p.bEdit) {
            Category c = p.getSelectedCategory();
            int id = c.getId();
            String name = JOptionPane.showInputDialog(null);
            if (!judge(name, p)){
                return;
            }
            new CategoryService().update(id, name);
        }else if (b == p.bDelete) {
            Category c = p.getSelectedCategory();
            if (c.getRecordNumber() != 0) {
                JOptionPane.showMessageDialog(p,"Cannot Be Deleted Due to the Existence of Records ");
                return;
            }
            new CategoryService().delete(c.getId());
        }

        p.updateData();
    }

    public boolean judge(String name, JPanel p) {
        if (name == null) {
            return false;
        }else if (name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(p, "Please Enter A New Category Name");
            return false;
        }
        return true;
    }
}
