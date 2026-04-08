package dk.zealand.hw_racekattekluben.application.interfaces;

import dk.zealand.hw_racekattekluben.domain.Exhibition;
import java.util.List;

public interface IExhibitionRepository {
    List<Exhibition> findAll();
    Exhibition findById(int id);
    void save(Exhibition exhibition);
    void update(Exhibition exhibition);
    void delete(int id);
    void registerCat(int catId, int exhibitionId);
}