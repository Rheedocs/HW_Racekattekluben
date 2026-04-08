package dk.zealand.hw_racekattekluben.domain.exceptions;

public class MemberAlreadyBreederException extends RuntimeException {
    public MemberAlreadyBreederException(int id) {
        super("Medlem med id " + id + " er allerede opdrætter");
    }
}
