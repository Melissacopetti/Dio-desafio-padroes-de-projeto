package main.java.dio.desafiopadroesdeprojeto.service;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import main.java.dio.desafiopadroesdeprojeto.service.business.PaymentBusiness;
import main.java.dio.desafiopadroesdeprojeto.controller.PaymentController;
import main.java.dio.desafiopadroesdeprojeto.service.HashManager;
import dio.desafiopadroesdeprojeto.service.IdGenerator;
import dio.desafiopadroesdeprojeto.service.TokenGenerator;
import dio.desafiopadroesdeprojeto.service.business.ClientBusiness;
import dio.desafiopadroesdeprojeto.controller.*;
import dio.desafiopadroesdeprojeto.routers.*;


public @Configuration public class SpringBootConfiguration {

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

    @Bean
    public ClientBusiness clientBusiness() {
        return new ClientBusiness();
    }

    @Bean
    public ClientController clientController() {
        return new ClientController();
    }

    @Bean
    public PaymentController paymentController() {
        return new PaymentController();
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
}
