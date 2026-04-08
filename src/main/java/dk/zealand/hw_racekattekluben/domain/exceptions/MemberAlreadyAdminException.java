package dk.zealand.hw_racekattekluben.domain.exceptions;

public class MemberAlreadyAdminException extends RuntimeException {
    public MemberAlreadyAdminException(int id) {
        super("Medlem med id " + id + " er allerede admin");
    }
}
