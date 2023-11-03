package dio.desafiopadroesdeprojeto.dataBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.mysql.cj.xdevapi.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public abstract class BaseDataBase {

    @Autowired
    private Environment environment;

    protected DataSource dataSource;

    public BaseDataBase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static int parseStringToInt(String value) {
        return Integer.parseInt(value);
    }

    protected String getDbHost() {
        return environment.getProperty("spring.datasource.url");
    }

    protected String getDbPort() {
        return environment.getProperty("spring.datasource.port");
    }

    protected String getDbUser() {
        return environment.getProperty("spring.datasource.username");
    }

    protected String getDbPassword() {
        return environment.getProperty("spring.datasource.password");
    }

    protected String getDbDatabaseName() {
        return environment.getProperty("spring.datasource.database-name");
    }
 public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

     public ResultSet executeQuery(String sql) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    public void update(String sql) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
    
}
