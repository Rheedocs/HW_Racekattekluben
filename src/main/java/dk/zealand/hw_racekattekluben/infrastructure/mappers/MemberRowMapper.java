package dk.zealand.hw_racekattekluben.infrastructure.mappers;

import java.sql.ResultSet;

import dk.zealand.hw_racekattekluben.domain.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;

//public class MemberRowMapper implements RowMapper<Member> {
//    @Override
//    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Member member = new Member(
//                rs.getInt("id"),
//                rs.getString("name"),
//                rs.getString("email"),
//                rs.getString("address")
//        );
//        return member;
//    }
//}
