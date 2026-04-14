package dk.zealand.hw_racekattekluben.presentation.helpers;

import dk.zealand.hw_racekattekluben.domain.Member;
import dk.zealand.hw_racekattekluben.domain.enums.Role;
import jakarta.servlet.http.HttpSession;

public class AuthHelper {

    public static Member getLoggedIn(HttpSession session) {
        return (Member) session.getAttribute("member");
    }

    public static boolean isAdmin(HttpSession session) {
        Member member = getLoggedIn(session);
        return member != null && member.getRole() == Role.ADMIN;
    }

    public static boolean isSelf(HttpSession session, int id) {
        Member member = getLoggedIn(session);
        return member != null && member.getId() == id;
    }

    public static boolean isAdminOrSelf(HttpSession session, int id) {
        return isAdmin(session) || isSelf(session, id);
    }
}