package dk.zealand.hw_racekattekluben.infrastructure;

import dk.zealand.hw_racekattekluben.application.interfaces.IExhibitionRepository;
import dk.zealand.hw_racekattekluben.domain.Exhibition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SQLExhibitionRepository implements IExhibitionRepository {

    @Override
    public List<Exhibition> findAll() { return null; }

    @Override
    public Exhibition findById(int id) { return null; }

    @Override
    public void save(Exhibition exhibition) {}

    @Override
    public void update(Exhibition exhibition) {}

    @Override
    public void delete(int id) {}

    @Override
    public void registerCat(int catId, int exhibitionId) {}
}