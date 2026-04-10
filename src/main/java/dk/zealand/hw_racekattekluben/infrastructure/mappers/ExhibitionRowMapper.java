package dk.zealand.hw_racekattekluben.infrastructure.mappers;

import dk.zealand.hw_racekattekluben.domain.Exhibition;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExhibitionRowMapper implements RowMapper<Exhibition> {

    @Override
    public Exhibition mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Exhibition(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("location"),
                rs.getDate("date").toLocalDate()
        );
    }
}