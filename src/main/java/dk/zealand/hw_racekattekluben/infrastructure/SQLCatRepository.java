package dk.zealand.hw_racekattekluben.infrastructure;

import dk.zealand.hw_racekattekluben.application.interfaces.ICatRepository;
import dk.zealand.hw_racekattekluben.domain.Cat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SQLCatRepository implements ICatRepository {

    @Override
    public List<Cat> findAll() { return null; }

    @Override
    public Cat findById(int id) { return null; }

    @Override
    public List<Cat> findByMemberId(int memberId) { return null; }

    @Override
    public void save(Cat cat) {}

    @Override
    public void update(Cat cat) {}

    @Override
    public void delete(int id) {}
}