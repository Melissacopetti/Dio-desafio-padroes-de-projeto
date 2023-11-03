package dio.desafiopadroesdeprojeto.dataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import dio.desafiopadroesdeprojeto.dao.ClientsRowMapper;


@Repository
public class ClientDataBase extends BaseDataBase {

    public ClientDataBase(DataSource dataSource) {
        super(dataSource);

    }

    private static final String TABLE = "clients";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createClient(Clients client) {
        jdbcTemplate.execute("INSERT INTO " + TABLE + " (id, name, email, password, cpf) VALUES (?, ?, ?, ?, ?)",
                new PreparedStatementCallback<Void>() {
                    @Override
                    public Void doInPreparedStatement(PreparedStatement ps) throws SQLException {
                        ps.setLong(1, client.getId());
                        ps.setString(2, client.getName());
                        ps.setString(3, client.getEmail());
                        ps.setString(4, client.getPassword());
                        ps.setString(5, client.getCpf());

                        ps.execute();

                        return null;
                    }
                });
    }

    public Clients clientLogin(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM " + TABLE + " WHERE email = ?",
                (RowMapper<Clients>) new ClientsRowMapper());
    }

}
