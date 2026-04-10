package dk.zealand.hw_racekattekluben.infrastructure;

import dk.zealand.hw_racekattekluben.application.interfaces.IExhibitionRepository;
import dk.zealand.hw_racekattekluben.domain.Exhibition;
import dk.zealand.hw_racekattekluben.infrastructure.mappers.ExhibitionRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SQLExhibitionRepository implements IExhibitionRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ExhibitionRowMapper exhibitionRowMapper;

    public SQLExhibitionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.exhibitionRowMapper = new ExhibitionRowMapper();
    }

    @Override
    public List<Exhibition> findAll() {
        String sql = "SELECT * FROM exhibition";
        return jdbcTemplate.query(sql, exhibitionRowMapper);
    }

    @Override
    public Exhibition findById(int id) {
        String sql = "SELECT * FROM exhibition WHERE id = ?";
        List<Exhibition> exhibitions = jdbcTemplate.query(sql, exhibitionRowMapper, id);
        return exhibitions.isEmpty() ? null : exhibitions.getFirst();
    }

    @Override
    public void save(Exhibition exhibition) {
        String sql = "INSERT INTO exhibition (name, location, date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, exhibition.getName(), exhibition.getLocation(), exhibition.getDate());
    }

    @Override
    public void update(Exhibition exhibition) {
        String sql = "UPDATE exhibition SET name = ?, location = ?, date = ? WHERE id = ?";
        jdbcTemplate.update(sql, exhibition.getName(), exhibition.getLocation(), exhibition.getDate(),
                exhibition.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM exhibition WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void registerCat(int catId, int exhibitionId) {
        String sql = "INSERT INTO cat_exhibition (cat_id, exhibition_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, catId, exhibitionId);
    }
}