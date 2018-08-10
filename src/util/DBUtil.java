package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "SpendLess";
    static String encoding = "utf8";
    static boolean useSSL = false;
    static String loginName = "root";
    static String pw = "admin";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&useSSL=%b", ip, port, database, encoding, useSSL);
        return DriverManager.getConnection(url, loginName, pw);
    }
}
