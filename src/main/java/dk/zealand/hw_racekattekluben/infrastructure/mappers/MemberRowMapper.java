package dk.zealand.hw_racekattekluben.infrastructure.mappers;

import dk.zealand.hw_racekattekluben.domain.Member;
import dk.zealand.hw_racekattekluben.domain.enums.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member member = new Member(
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"),
                Role.valueOf(rs.getString("role")),
                rs.getBoolean("is_breeder")
        );
        member.setId(rs.getInt("id"));
        return member;
    }
}