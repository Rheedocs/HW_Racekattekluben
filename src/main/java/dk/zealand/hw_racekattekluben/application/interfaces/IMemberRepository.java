package dk.zealand.hw_racekattekluben.application.interfaces;

import dk.zealand.hw_racekattekluben.domain.Member;
import java.util.List;

public interface IMemberRepository {
    List<Member> findAll();
    Member findById(int id);
    Member findByEmail(String email);
    void save(Member member);
    void update(Member member);
    void delete(int id);
}