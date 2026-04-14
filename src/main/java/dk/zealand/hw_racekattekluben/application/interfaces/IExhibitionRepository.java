package dk.zealand.hw_racekattekluben.application.interfaces;

import dk.zealand.hw_racekattekluben.domain.CatExhibitionEntry;
import dk.zealand.hw_racekattekluben.domain.Exhibition;
import java.util.List;

public interface IExhibitionRepository {
    List<Exhibition> findAll();
    Exhibition findById(int id);
    List<CatExhibitionEntry> findCatsByExhibitionId(int exhibitionId);
    List<Integer> findRegisteredCatIds(int exhibitionId);
    void save(Exhibition exhibition);
    void update(Exhibition exhibition);
    void delete(int id);
    void registerCat(int catId, int exhibitionId);
}