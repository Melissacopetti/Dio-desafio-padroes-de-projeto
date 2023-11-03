package main.java.dio.desafiopadroesdeprojeto.service;

import javax.sql.DataSource;

import dio.desafiopadroesdeprojeto.business.PaymentBusiness;
import dio.desafiopadroesdeprojeto.controller.PaymentController;
import dio.desafiopadroesdeprojeto.service.HashManager;
import dio.desafiopadroesdeprojeto.service.IdGenerator;
import dio.desafiopadroesdeprojeto.service.TokenGenerator;
import main.java.dio.desafiopadroesdeprojeto.controller.ClientController;


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
        return new HashManager();
    }

    @Bean
    public PaymentPreparedStatementCallBack paymentPreparedStatementCallBack() {
        return new PaymentPreparedStatementCallBack();
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
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/my_database");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }
}
