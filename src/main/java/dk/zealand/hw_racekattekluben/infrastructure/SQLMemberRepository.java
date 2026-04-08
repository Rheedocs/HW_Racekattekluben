package dk.zealand.hw_racekattekluben.infrastructure;

import dk.zealand.hw_racekattekluben.application.interfaces.IMemberRepository;
import dk.zealand.hw_racekattekluben.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SQLMemberRepository implements IMemberRepository {

    @Override
    public List<Member> findAll() { return null; }

    @Override
    public Member findById(int id) { return null; }

    @Override
    public Member findByEmail(String email) { return null; }

    @Override
    public void save(Member member) {}

    @Override
    public void update(Member member) {}

    @Override
    public void delete(int id) {}
}