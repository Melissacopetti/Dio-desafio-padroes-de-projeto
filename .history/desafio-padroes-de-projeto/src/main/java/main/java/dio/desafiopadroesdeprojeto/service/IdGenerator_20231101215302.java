package main.java.dio.desafiopadroesdeprojeto.service;

import java.util.UUID;

public class IdGenerator {
    public String generateId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}

