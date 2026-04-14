package dk.zealand.hw_racekattekluben.application.service;

import dk.zealand.hw_racekattekluben.application.interfaces.IMemberRepository;
import dk.zealand.hw_racekattekluben.domain.Member;
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

    public Member login(String email, String password) {
        Member member = getByEmail(email);
        if (!passwordEncoder.matches(password, member.getPassword()))
            throw new IllegalArgumentException("Forkert email eller adgangskode");
        return member;
    }

    public void create(Member member) {
        validateMember(member);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    public void update(Member member) {
        validateMember(member);
        memberRepository.update(member);
    }

    public void delete(int id) {
        if (id <= 0) throw new IllegalArgumentException("Ugyldigt medlem-id");
        Member member = memberRepository.findById(id);
        if (member == null) throw new MemberNotFoundException(id);
        memberRepository.delete(id);
    }

    private void validateMember(Member member) {
        if (member == null) throw new IllegalArgumentException("Medlem må ikke være null");
        if (member.getName() == null || member.getName().isBlank()) throw new IllegalArgumentException("Navn må ikke være tomt");
        if (member.getEmail() == null || member.getEmail().isBlank() || !member.getEmail().matches(".+@.+\\..+"))
            throw new IllegalArgumentException("Email må ikke være tom og skal indeholde @ og punktum");
        if (member.getRole() == null) throw new IllegalArgumentException("Rolle må ikke være tom");
    }
}