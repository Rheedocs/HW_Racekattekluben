package dk.zealand.hw_racekattekluben.domain.exceptions;

public class CatNotFoundException extends RuntimeException {
    public CatNotFoundException(int id) {
        super("Kat med id " + id + " blev ikke fundet");
    }
}
