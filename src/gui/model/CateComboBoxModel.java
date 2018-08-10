package gui.model;

import entity.Category;
import service.CategoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class CateComboBoxModel implements ComboBoxModel {
    public List<Category> cs;
    public Category c;

    public CateComboBoxModel(){
        cs = new CategoryService().list();
        if (!cs.isEmpty()) {
            c = cs.get(0);
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    @Override
    public Object getSelectedItem() {
        if (!cs.isEmpty()) {
            return c;
        }else{
            return null;
        }
    }

    @Override
    public int getSize() {
        return cs.size();
    }

    @Override
    public Object getElementAt(int index) {
        return cs.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
