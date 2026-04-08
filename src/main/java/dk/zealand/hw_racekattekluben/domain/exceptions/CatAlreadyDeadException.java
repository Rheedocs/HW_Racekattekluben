package dk.zealand.hw_racekattekluben.domain.exceptions;

public class CatAlreadyDeadException extends RuntimeException {
    public CatAlreadyDeadException(int id) {
        super("Kat med id " + id + " er allerede død");
    }
}
