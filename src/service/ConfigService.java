package service;

import dao.ConfigDAO;
import entity.Config;

public class ConfigService {
    public static ConfigDAO dao = new ConfigDAO();

    public static String budget = "budget";
    public static String mysqlpath = "mysqlpath";
    public static String default_budget = "500";

    static {
        init();
    }

    public static void init(){
        init(budget, default_budget);
        init(mysqlpath, "");
    }

    public static void init(String key, String value) {
        Config config = dao.getByKey(key);
        if (config == null) {
            Config con = new Config();
            con.setKey(key);
            con.setValue(value);
            dao.add(con);
        }
    }

    public String get(String key) {
        return dao.getByKey(key).getValue();
    }

    public void update(String key, String value) {
        Config config = dao.getByKey(key);
        config.setValue(value);
        dao.update(config);
    }

    public int getIntBudget(){
        return Integer.parseInt(get(budget));
    }
}
