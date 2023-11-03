package main.java.dio.desafiopadroesdeprojeto.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashManager {

    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    public HashManager(BCryptPasswordEncoder bcryptPasswordEncoder) {
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    }

    public String hash(String text) {
        return bcryptPasswordEncoder.encode(text);
    }

    public boolean compare(String text, String hash) {
        return bcryptPasswordEncoder.matches(text, hash);
    }

    public String hashPassword(String password) {
        return null;
    }
}
