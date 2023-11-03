package main.java.dio.desafiopadroesdeprojeto.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CpfValidator {
     public boolean isValidCpf(String cpf) {
        Pattern pattern = Pattern.compile("^[0-9]{11}$");
        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
    }
}
