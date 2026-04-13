package dk.zealand.hw_racekattekluben.infrastructure;

import dk.zealand.hw_racekattekluben.application.interfaces.IMemberRepository;
import dk.zealand.hw_racekattekluben.domain.Member;
import dk.zealand.hw_racekattekluben.infrastructure.mappers.MemberRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SQLMemberRepository implements IMemberRepository {

    private final JdbcTemplate jdbcTemplate;
    private final MemberRowMapper memberRowMapper;

    public SQLMemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.memberRowMapper = new MemberRowMapper();
    }

    @Override
    public List<Member> findAll() {
        String sql = "SELECT * FROM member";
        return jdbcTemplate.query(sql, memberRowMapper);
    }

    @Override
    public Member findById(int id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        List<Member> members = jdbcTemplate.query(sql, memberRowMapper, id);
        return members.isEmpty() ? null : members.getFirst();
    }

    @Override
    public Member findByEmail(String email) {
        String sql = "SELECT * FROM member WHERE email = ?";
        List<Member> members = jdbcTemplate.query(sql, memberRowMapper, email);
        return members.isEmpty() ? null : members.getFirst();
    }

    @Override
    public void save(Member member) {
        String sql = "INSERT INTO member (name, email, password, role, is_breeder) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, member.getName(), member.getEmail(), member.getPassword(), member.getRole().name(),
                member.isBreeder());
    }

    @Override
    public void update(Member member) {
        String sql = "UPDATE member SET name = ?, email = ?, password = ?, role = ?, is_breeder = ? WHERE id = ?";
        jdbcTemplate.update(sql, member.getName(), member.getEmail(), member.getPassword(), member.getRole().name(),
                member.isBreeder(), member.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM member WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}