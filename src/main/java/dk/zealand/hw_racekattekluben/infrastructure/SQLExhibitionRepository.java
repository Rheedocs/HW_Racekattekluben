package dk.zealand.hw_racekattekluben.infrastructure;

import dk.zealand.hw_racekattekluben.application.interfaces.IExhibitionRepository;
import dk.zealand.hw_racekattekluben.domain.Cat;
import dk.zealand.hw_racekattekluben.domain.CatExhibitionEntry;
import dk.zealand.hw_racekattekluben.domain.Exhibition;
import dk.zealand.hw_racekattekluben.infrastructure.mappers.CatRowMapper;
import dk.zealand.hw_racekattekluben.infrastructure.mappers.ExhibitionRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SQLExhibitionRepository implements IExhibitionRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ExhibitionRowMapper exhibitionRowMapper;
    private final CatRowMapper catRowMapper;

    private static final String BASE_SQL = "SELECT id, name, location, date FROM exhibition";

    public SQLExhibitionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.exhibitionRowMapper = new ExhibitionRowMapper();
        this.catRowMapper = new CatRowMapper();
    }

    @Override
    public List<Exhibition> findAll() {
        return jdbcTemplate.query(BASE_SQL, exhibitionRowMapper);
    }

    @Override
    public Exhibition findById(int id) {
        List<Exhibition> exhibitions = jdbcTemplate.query(BASE_SQL + " WHERE id = ?", exhibitionRowMapper, id);
        return exhibitions.isEmpty() ? null : exhibitions.getFirst();
    }

    @Override
    public List<CatExhibitionEntry> findCatsByExhibitionId(int exhibitionId) {
        String sql = """
        SELECT c.id, c.name, c.birthdate, c.deathdate, c.ems_code, c.breeder_name,
               c.image_path, c.member_id, c.mother_id, c.father_id,
               m.name AS mother_name, f.name AS father_name,
               ce.placement
        FROM cat c
        JOIN cat_exhibition ce ON c.id = ce.cat_id
        LEFT JOIN cat m ON c.mother_id = m.id
        LEFT JOIN cat f ON c.father_id = f.id
        WHERE ce.exhibition_id = ?
        ORDER BY ce.placement ASC
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Cat cat = catRowMapper.mapRow(rs, rowNum);
            String placement = rs.getString("placement");
            return new CatExhibitionEntry(cat, placement);
        }, exhibitionId);
    }

    @Override
    public List<Integer> findRegisteredCatIds(int exhibitionId) {
        String sql = "SELECT cat_id FROM cat_exhibition WHERE exhibition_id = ?";
        return jdbcTemplate.queryForList(sql, Integer.class, exhibitionId);
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