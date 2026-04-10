package dk.zealand.hw_racekattekluben.infrastructure.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super("Databasefejl: " + message);
    }

    public DatabaseException(String message, Throwable cause) {
        super("Databasefejl: " + message, cause);
    }
}
