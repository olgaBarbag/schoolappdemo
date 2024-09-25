package gr.aueb.cf.schoolappdemo.service.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {

    private static Connection connection;
    private static BasicDataSource dataSource = new BasicDataSource();

    static {
          dataSource.setUrl("jdbc:mysql://localhost:3306/schoolappdemo?serverTimezone=UTC");
          dataSource.setUsername("school2024_user");
          dataSource.setPassword(System.getenv("pass_school2024DB"));
        //This settings should be compatible with tomcat configs
          dataSource.setInitialSize(10);
          dataSource.setMaxTotal(50);
          dataSource.setMinIdle(10);
          dataSource.setMaxIdle(10);
    }

    private DBUtil() {

    }

    public static Connection getConnection() throws SQLException {

        try {
            //In enterprise apps where dependencies are used
            //MySql driver is not loading automatically into the memory
            //.forName LOADS the included class in memory
            //so then we can use instances
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = dataSource.getConnection();
            System.out.println("Server works!");
            return connection;
        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        }
    }
}
