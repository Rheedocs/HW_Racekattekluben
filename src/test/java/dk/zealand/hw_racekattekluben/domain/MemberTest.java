package dk.zealand.hw_racekattekluben.domain;

import dk.zealand.hw_racekattekluben.domain.enums.Role;
import dk.zealand.hw_racekattekluben.domain.exceptions.MemberAlreadyAdminException;
import dk.zealand.hw_racekattekluben.domain.exceptions.MemberAlreadyBreederException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("Anders Nielsen", "anders@mail.dk", "password123",
                Role.USER, false);
    }

    @Test
    void promote_withUserRole_setsRoleToAdmin() {
        member.promote();
        assertEquals(Role.ADMIN, member.getRole());
    }

    @Test
    void promote_whenAlreadyAdmin_throwsMemberAlreadyAdminException() {
        member.promote();
        assertThrows(MemberAlreadyAdminException.class, () -> member.promote());
    }

    @Test
    void becomeBreeder_whenNotBreeder_setsIsBreederToTrue() {
        member.becomeBreeder();
        assertTrue(member.isBreeder());
    }

    @Test
    void becomeBreeder_whenAlreadyBreeder_throwsMemberAlreadyBreederException() {
        member.becomeBreeder();
        assertThrows(MemberAlreadyBreederException.class, () -> member.becomeBreeder());
    }
}