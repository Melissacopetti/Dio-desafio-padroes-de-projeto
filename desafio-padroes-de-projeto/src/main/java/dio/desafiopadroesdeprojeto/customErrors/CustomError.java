package dio.desafiopadroesdeprojeto.customErrors;

public class CustomError extends RuntimeException {
    private int statusCode;

    public CustomError(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}


