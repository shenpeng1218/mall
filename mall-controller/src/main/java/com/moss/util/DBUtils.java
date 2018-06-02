package com.moss.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtils {

    private static Properties props;

    static {
        try {
            InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("application.yml");
            props = new Properties();
            props.load(in);
            in.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws Exception{
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = "123abc";
        String driver = props.getProperty("driver-class-name");
        Class.forName(driver);
        return DriverManager.getConnection(url,username, password);
    }
}
