package dio.desafiopadroesdeprojeto.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import dio.desafiopadroesdeprojeto.model.Clients.Clients;

public class ClientsRowMapper implements RowMapper<Clients> {

    @Override
    public Clients mapRow(ResultSet rs, int rowNum) throws SQLException {
        Clients client = new Clients();
        client.setId(rs.getLong("id"));
        client.setName(rs.getString("name"));
        client.setEmail(rs.getString("email"));
        client.setPassword(rs.getString("password"));
        client.setCpf(rs.getString("cpf"));
        return client;
    }

}
