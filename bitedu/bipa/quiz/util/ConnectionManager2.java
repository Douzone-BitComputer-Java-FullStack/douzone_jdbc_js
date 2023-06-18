package bitedu.bipa.quiz.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager2 {
	// 싱글톤으로 작성
	// data폴더의 db.properties를 이용하여 컨넥션 생성

    private static final Properties properties = new Properties();

    public static Connection getConnection() {

        Connection con = null;

        try (FileReader fileReader = new FileReader("data/db.properties")) {
            properties.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String jdbcURL = (String) properties.get("jdbcURL");
        String driver = (String) properties.get("driver");
        String id = (String) properties.get("id");
        String pwd = (String) properties.get("pwd");

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(jdbcURL,id,pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
