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

    public SQLCatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.catRowMapper = new CatRowMapper();
    }

    @Override
    public List<Cat> findAll() {
        String sql = "SELECT * FROM cat";
        return jdbcTemplate.query(sql, catRowMapper);
    }

    @Override
    public Cat findById(int id) {
        String sql = "SELECT * FROM cat WHERE id = ?";
        List<Cat> cats = jdbcTemplate.query(sql, catRowMapper, id);
        return cats.isEmpty() ? null : cats.getFirst();
    }

    @Override
    public List<Cat> findByMemberId(int memberId) {
        String sql = "SELECT * FROM cat WHERE member_id = ?";
        return jdbcTemplate.query(sql, catRowMapper, memberId);
    }

    @Override
    public void save(Cat cat) {
        String sql = "INSERT INTO cat (name, birthdate, ems_code, breeder_name, member_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cat.getName(), cat.getBirthdate(), cat.getEmsCode(), cat.getBreederName(),
                cat.getMemberId());
    }

    @Override
    public void update(Cat cat) {
        String sql = "UPDATE cat SET name = ?, birthdate = ?, ems_code = ?, breeder_name = ?, deathdate = ?," +
                " mother_id = ?, father_id = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                cat.getName(),
                cat.getBirthdate(),
                cat.getEmsCode(),
                cat.getBreederName(),
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