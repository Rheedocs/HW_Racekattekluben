package dk.zealand.hw_racekattekluben.application.service;

import dk.zealand.hw_racekattekluben.application.interfaces.IMemberRepository;
import dk.zealand.hw_racekattekluben.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final IMemberRepository memberRepository;

    public MemberService(IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAll() {
        return null;
    }

    public Member getById(int id) {
        return null;
    }

    public Member getByEmail(String email) {
        return null;
    }

    public void create(Member member) {
    }

    public void update(Member member) {
    }

    public void delete(int id) {
    }
}