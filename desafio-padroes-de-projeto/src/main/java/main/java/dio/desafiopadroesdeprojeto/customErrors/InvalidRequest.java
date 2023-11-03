package main.java.dio.desafiopadroesdeprojeto.customErrors;

public class InvalidRequest extends CustomError {
    public InvalidRequest(String string, Exception e) {
        super(400, "Incorrect data");
    }

 

}