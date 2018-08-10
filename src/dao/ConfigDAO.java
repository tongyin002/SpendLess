package dao;

import entity.Config;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigDAO {

    public void add(Config config){
        String sql = "insert into config values (null, ?, ?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, config.key);
            ps.setString(2, config.value);
            ps.execute();
            ResultSet rs =ps.getGeneratedKeys();
            if (rs.next()){
                config.setId(rs.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(Config config) {
        String sql = "update config set key_ = ?, value = ? where id = ?";
        try (Connection c = DBUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, config.key);
            ps.setString(2, config.value);
            ps.setInt(3, config.id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int id){

        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement()) {
            String sql = "delete from config where id = " + id;
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Config get(int id){
        Config config = null;
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement()) {
            String sql = "select * from config where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                config = new Config();
                config.setId(id);
                config.setKey(rs.getString("key_"));
                config.setValue(rs.getString("value"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return config;
    }

    public List<Config> list(){
        return list(0, Short.MAX_VALUE);
    }

    public List<Config> list(int start, int count){
        String sql = "select * from config order by id desc limit ?, ?";
        List<Config> configs = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Config config = new Config();
                config.setId(rs.getInt("id"));
                config.setKey(rs.getString("key_"));
                config.setValue(rs.getString("value"));
                configs.add(config);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return configs;
    }

    public int getTotal(){
        int re = 0;
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement()) {
            String sql = "select count(*) from config";
            ResultSet rs =s.executeQuery(sql);
            if (rs.next()) {
                re = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return re;
    }

    public Config getByKey(String key){
        Config config = null;
        String sql = "select * from config where key_ = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                config = new Config();
                config.setId(rs.getInt("id"));
                config.setKey(key);
                config.setValue(rs.getString("value"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return config;
    }
}
