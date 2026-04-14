package dk.zealand.hw_racekattekluben.domain;

import dk.zealand.hw_racekattekluben.domain.enums.Role;
import dk.zealand.hw_racekattekluben.domain.exceptions.MemberAlreadyAdminException;
import dk.zealand.hw_racekattekluben.domain.exceptions.MemberAlreadyBreederException;

public class Member {
    private int id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private boolean isBreeder;

    public Member(){}

    public Member(String name, String email, String password, Role role, boolean isBreeder) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isBreeder = isBreeder;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    public boolean isBreeder() { return isBreeder; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    public void promote() {
        if (this.role == Role.ADMIN) throw new MemberAlreadyAdminException(this.id);
        this.role = Role.ADMIN;
    }

    public void becomeBreeder() {
        if (this.isBreeder) throw new MemberAlreadyBreederException(this.id);
        this.isBreeder = true;
    }

    public void removeBreeder() {
        if (!this.isBreeder) throw new IllegalArgumentException("Medlemmet er ikke opdrætter");
        this.isBreeder = false;
    }

    @Override
    public String toString() {
        return "Member{id=" + id + ", name='" + name + "', email='" + email + "', role=" + role + ", password='[hidden]'}";
    }
}