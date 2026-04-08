package dk.zealand.hw_racekattekluben.application.service;

import dk.zealand.hw_racekattekluben.application.interfaces.IExhibitionRepository;
import dk.zealand.hw_racekattekluben.domain.Exhibition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExhibitionService {

    private final IExhibitionRepository exhibitionRepository;

    public ExhibitionService(IExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
    }

    public List<Exhibition> getAll() {
        return null;
    }

    public Exhibition getById(int id) {
        return null;
    }

    public void create(Exhibition exhibition) {
    }

    public void update(Exhibition exhibition) {
    }

    public void delete(int id) {
    }

    public void registerCat(int catId, int exhibitionId) {
    }
}