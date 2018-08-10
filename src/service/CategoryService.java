package service;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

import java.util.List;

public class CategoryService {
    public CategoryDAO categoryDAO = new CategoryDAO();
    public RecordDAO recordDAO = new RecordDAO();

    public List<Category> list() {
        List<Category> list = categoryDAO.list();
        for (Category c : list) {
            List<Record> records = recordDAO.list(c.getId());
            c.setRecordNumber(records.size());
        }
        list.sort((c1, c2) -> c2.getRecordNumber() - c1.getRecordNumber());
        return list;
    }

    public void add(String name) {
        Category category = new Category();
        category.setName(name);
        categoryDAO.add(category);
    }

    public void update(int id, String name) {
        Category category = new Category();
        category.setName(name);
        category.setId(id);
        categoryDAO.update(category);
    }

    public void delete(int id) {
        categoryDAO.delete(id);
    }
}
