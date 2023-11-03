package main.java.dio.desafiopadroesdeprojeto.service;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import main.java.dio.desafiopadroesdeprojeto.service.business.PaymentBusiness;
import main.java.dio.desafiopadroesdeprojeto.controller.PaymentController;
import main.java.dio.desafiopadroesdeprojeto.service.HashManager;
import main.java.dio.desafiopadroesdeprojeto.service.IdGenerator;
import main.java.dio.desafiopadroesdeprojeto.service.TokenGenerator;
import main.java.dio.desafiopadroesdeprojeto.service.business.ClientBusiness;
import main.java.dio.desafiopadroesdeprojeto.controller.*;
import main.java.dio.desafiopadroesdeprojeto.routers.*;


public @Configuration public class SpringBootConfig {

    @Bean
    public IdGenerator idGenerator() {
        return new IdGenerator();
    }

    @Bean
    public TokenGenerator tokenGenerator() {
        return new TokenGenerator();
    }

    @Bean
    public HashManager hashManager() {
        return new HashManager(null);
    }

    @Bean
    public PaymentPreparedStatementCallback paymentPreparedStatementCallBack() {
        return new PaymentPreparedStatementCallback();
    }

    @Bean
    public PaymentBusiness paymentBusiness() {
        return new PaymentBusiness();
    }

    // Removeu o bean PaymentController do método paymentRouter()
    @Bean
    public ClientBusiness clientBusiness() {
        return new ClientBusiness();
    }

    @Bean
    public ClientController clientController() {
        return new ClientController();
    }

    @Bean
    public ClientRouter clientRouter() {
        return new ClientRouter();
    }

    @Bean
    public PaymentRouter paymentRouter() {
        return new PaymentRouter();
    }

     @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/my_database");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Override
    public String toString() {
        return "SpringBootConfiguration []";
    }
}
