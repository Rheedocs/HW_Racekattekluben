package dk.zealand.hw_racekattekluben.application.service;

import dk.zealand.hw_racekattekluben.application.interfaces.IMemberRepository;
import dk.zealand.hw_racekattekluben.domain.Member;
import dk.zealand.hw_racekattekluben.domain.enums.Role;
import dk.zealand.hw_racekattekluben.domain.exceptions.MemberNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class MemberService {

    private final IMemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MemberService(IMemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    public Member getById(int id) {
        if (id <= 0) throw new IllegalArgumentException("Id skal være større end nul");
        Member member = memberRepository.findById(id);
        if (member == null) throw new MemberNotFoundException(id);
        return member;
    }

    public Member getByEmail(String email) {
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email må ikke være tom");
        Member member = memberRepository.findByEmail(email);
        if (member == null) throw new MemberNotFoundException(email);
        return member;
    }

    /**
     * Henter alle medlemmer sorteret så det loggede medlem vises øverst.
     *
     * @param loggedInId det loggede medlems id
     * @return sorteret liste af medlemmer
     */
    public List<Member> getAllSortedByLoggedIn(int loggedInId) {
        List<Member> members = getAll();
        members.sort((a, b) -> {
            if (a.getId() == loggedInId) return -1;
            if (b.getId() == loggedInId) return 1;
            return 0;
        });
        return members;
    }

    public Member login(String email, String password) {
        Member member = getByEmail(email);
        if (!passwordEncoder.matches(password, member.getPassword()))
            throw new IllegalArgumentException("Forkert email eller adgangskode");
        return member;
    }

    public void create(String name, String email, String password, boolean breeder) {
        Member member = new Member(name, email, password, Role.USER, breeder);
        validateMember(member);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    public void update(Member member, boolean breeder) {
        Member existing = getById(member.getId());
        existing.setName(member.getName());
        existing.setEmail(member.getEmail());
        if (breeder && !existing.isBreeder()) existing.becomeBreeder();
        if (!breeder && existing.isBreeder()) existing.removeBreeder();
        existing.setPassword(existing.getPassword());
        validateMember(existing);
        memberRepository.update(existing);
    }

    public void delete(int id) {
        if (id <= 0) throw new IllegalArgumentException("Ugyldigt medlem-id");
        getById(id);
        memberRepository.delete(id);
    }

    /**
     * Tjekker om et givet medlem er den eneste admin i systemet.
     * Bruges til at forhindre sletning af den sidste admin.
     *
     * @param id medlemmets id
     * @return true hvis medlemmet er den eneste admin, false ellers
     */
    public boolean isOnlyAdmin(int id) {
        int adminCount = 0;
        for (Member member : getAll()) if (member.getRole() == Role.ADMIN) adminCount++;
        return adminCount == 1 && getById(id).getRole() == Role.ADMIN;
    }

    private boolean isValidEmail(String email) {
        return email != null && !email.isBlank() &&
                email.matches("[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}");
    }

    private void validateMember(Member member) {
        if (member == null) throw new IllegalArgumentException("Medlem må ikke være null");
        if (member.getName() == null || member.getName().isBlank())
            throw new IllegalArgumentException("Navn må ikke være tomt");
        if (!isValidEmail(member.getEmail()))
            throw new IllegalArgumentException("Email er ikke gyldig");
        if (member.getRole() == null)
            throw new IllegalArgumentException("Rolle må ikke være tom");
    }
}