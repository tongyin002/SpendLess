package dao;

import entity.Category;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public void add(Category category) {
        String sql = "insert into category values (null, ?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.name);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                category.setId(rs.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement()) {
            String sql = "delete from category where id = " + id;
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(Category category) {
        String sql = "update category set name = ? where id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, category.name);
            ps.setInt(2, category.id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Category get(int id) {
        Category category = null;
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement()) {
            String sql = "select * from category where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()){
                category = new Category();
                category.setId(id);
                category.setName(rs.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return category;
    }

    public List<Category> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Category> list(int start, int count) {
        String sql = "select * from category order by id desc limit ?, ?";
        List<Category> categories = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }

    public int getTotal(){
        int re = 0;
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement()) {
            String sql = "select count(*) from category";
            ResultSet rs =s.executeQuery(sql);
            if (rs.next()) {
                re = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return re;
    }
}
