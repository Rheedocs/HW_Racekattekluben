package dk.zealand.hw_racekattekluben.domain.exceptions;

public class InvalidDeathdateException extends RuntimeException {
    public InvalidDeathdateException() {
        super("Dødsdato kan ikke være i fremtiden");
    }
}
