package dk.zealand.hw_racekattekluben.infrastructure.mappers;

import dk.zealand.hw_racekattekluben.domain.Cat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CatRowMapper implements RowMapper<Cat> {

    private LocalDate toLocalDate(ResultSet rs, String column) throws SQLException {
        return rs.getDate(column) != null ? rs.getDate(column).toLocalDate() : null;
    }

    @Override
    public Cat mapRow(ResultSet rs, int rowNum) throws SQLException {
        LocalDate birthdate = toLocalDate(rs, "birthdate");
        LocalDate deathdate = toLocalDate(rs, "deathdate");

        int motherIdRaw = rs.getInt("mother_id");
        Integer motherId = rs.wasNull() ? null : motherIdRaw;

        int fatherIdRaw = rs.getInt("father_id");
        Integer fatherId = rs.wasNull() ? null : fatherIdRaw;

        Cat cat = new Cat(
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

        cat.setMotherName(rs.getString("mother_name"));
        cat.setFatherName(rs.getString("father_name"));
        cat.setImagePath(rs.getString("image_path"));

        return cat;
    }
}