package dk.zealand.hw_racekattekluben.domain.exceptions;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(int id) {
        super("Medlem med id " + id + " blev ikke fundet");
    }

    public MemberNotFoundException(String email) {
        super("Medlem med email " + email + " blev ikke fundet");
    }
}
