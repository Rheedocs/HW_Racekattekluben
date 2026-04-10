package dk.zealand.hw_racekattekluben.infrastructure.mappers;

import dk.zealand.hw_racekattekluben.domain.Cat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CatRowMapper implements RowMapper<Cat> {

    @Override
    public Cat mapRow(ResultSet rs, int rowNum) throws SQLException {
        LocalDate birthdate = rs.getDate("birthdate") != null
                ? rs.getDate("birthdate").toLocalDate() : null;
        LocalDate deathdate = rs.getDate("deathdate") != null
                ? rs.getDate("deathdate").toLocalDate() : null;

        int motherIdRaw = rs.getInt("mother_id");
        Integer motherId = rs.wasNull() ? null : motherIdRaw;

        int fatherIdRaw = rs.getInt("father_id");
        Integer fatherId = rs.wasNull() ? null : fatherIdRaw;

        return new Cat(
                rs.getInt("id"),
                rs.getString("name"),
                birthdate,
                deathdate,
                rs.getString("ems_code"),
                rs.getString("breeder_name"),
                rs.getInt("member_id"),
                motherId,
                fatherId
        );
    }
}