package dk.zealand.hw_racekattekluben.domain.exceptions;

public class ExhibitionNotFoundException extends RuntimeException {
    public ExhibitionNotFoundException(int id) {
        super("Udstilling med id " + id + " blev ikke fundet");
    }
}
