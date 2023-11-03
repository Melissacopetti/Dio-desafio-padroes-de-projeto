package main.java.dio.desafiopadroesdeprojeto.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
     public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
