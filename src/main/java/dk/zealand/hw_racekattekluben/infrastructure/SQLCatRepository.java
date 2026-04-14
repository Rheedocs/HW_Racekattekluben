package dk.zealand.hw_racekattekluben.infrastructure;

import dk.zealand.hw_racekattekluben.application.interfaces.ICatRepository;
import dk.zealand.hw_racekattekluben.domain.Cat;
import dk.zealand.hw_racekattekluben.infrastructure.mappers.CatRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SQLCatRepository implements ICatRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CatRowMapper catRowMapper;

    private static final String BASE_SQL = """
            SELECT c.id, c.name, c.birthdate, c.deathdate, c.ems_code, c.breeder_name,
                   c.image_path, c.member_id, c.mother_id, c.father_id,
                   m.name AS mother_name, f.name AS father_name
            FROM cat c
            LEFT JOIN cat m ON c.mother_id = m.id
            LEFT JOIN cat f ON c.father_id = f.id
            """;

    public SQLCatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.catRowMapper = new CatRowMapper();
    }

    @Override
    public List<Cat> findAll() {
        return jdbcTemplate.query(BASE_SQL, catRowMapper);
    }

    @Override
    public Cat findById(int id) {
        String sql = BASE_SQL + "WHERE c.id = ?";
        List<Cat> cats = jdbcTemplate.query(sql, catRowMapper, id);
        return cats.isEmpty() ? null : cats.getFirst();
    }

    @Override
    public List<Cat> findByMemberId(int memberId) {
        String sql = BASE_SQL + "WHERE c.member_id = ?";
        return jdbcTemplate.query(sql, catRowMapper, memberId);
    }

    @Override
    public void save(Cat cat) {
        String sql = "INSERT INTO cat (name, birthdate, ems_code, breeder_name, image_path, member_id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cat.getName(), cat.getBirthdate(), cat.getEmsCode(),
                cat.getBreederName(), cat.getImagePath(), cat.getMemberId());
    }

    @Override
    public void update(Cat cat) {
        String sql = """
                UPDATE cat SET name = ?, birthdate = ?, ems_code = ?, breeder_name = ?,
                image_path = ?, deathdate = ?, mother_id = ?, father_id = ? WHERE id = ?
                """;
        jdbcTemplate.update(sql,
                cat.getName(),
                cat.getBirthdate(),
                cat.getEmsCode(),
                cat.getBreederName(),
                cat.getImagePath(),
                cat.getDeathdate(),
                cat.getMotherId(),
                cat.getFatherId(),
                cat.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM cat WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}