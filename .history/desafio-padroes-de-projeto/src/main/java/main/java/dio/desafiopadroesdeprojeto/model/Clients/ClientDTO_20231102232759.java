package main.java.dio.desafiopadroesdeprojeto.model.Clients;


public class ClientDTO {
    private String 
    private String name;
    private String email;
    private String password;
    private String cpf;


    public ClientDTO() {
    }
    
    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

