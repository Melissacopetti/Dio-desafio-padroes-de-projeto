package dio.desafiopadroesdeprojeto.model.Clients;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties("password")
public class AuthenticationData {

    @JsonProperty("user_id")
    private String id;

    public String getId() {
        return id;
    }
}