package dio.desafiopadroesdeprojeto.dataBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
}
