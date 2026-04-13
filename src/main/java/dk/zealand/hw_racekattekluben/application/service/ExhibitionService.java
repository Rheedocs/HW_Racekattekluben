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
        return exhibitionRepository.findAll();
    }

    public Exhibition getById(int id) {
        if (id <= 0){
            throw new IllegalArgumentException("Id skal være større end 0");
        }
        Exhibition exhibition = exhibitionRepository.findById(id);
        if (exhibition == null){
            throw new IllegalArgumentException("Exhibition kunne ikke blive fundet");
        }
        return exhibition;
    }

    public void create(Exhibition exhibition) {
        validateExhibition(exhibition);
        exhibitionRepository.save(exhibition);
    }

    public void update(Exhibition exhibition) {
        validateExhibition(exhibition);
        exhibitionRepository.update(exhibition);
    }

    private void validateExhibition(Exhibition exhibition){
        if (exhibition.getId() <= 0){
            throw new IllegalArgumentException("Id skal være større end 0");
        }
        if (exhibition.getLocation() == null){
            throw new IllegalArgumentException("Location må ikke være tom");
        }
        if (exhibition.getName() == null){
            throw new IllegalArgumentException("Navn må ikke være tom");
        }
        if (exhibition.getDate() == null){
            throw new IllegalArgumentException("Datoen må ikke være tom");
        }
    }

    public void delete(int id, Exhibition exhibition) {
        validateExhibition(exhibition);
        exhibitionRepository.delete(id);
    }

    public void registerCat(int catId, int exhibitionId) {
        if (catId >= 0){
            throw new IllegalArgumentException("Kat eksiterer ikke");
        }
        if (exhibitionId >= 0){
            throw new IllegalArgumentException("Exhibition eksisterer ikke");
        }
        exhibitionRepository.registerCat(catId, exhibitionId);
    }
}