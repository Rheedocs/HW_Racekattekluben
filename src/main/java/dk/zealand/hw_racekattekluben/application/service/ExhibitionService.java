package dk.zealand.hw_racekattekluben.application.service;

import dk.zealand.hw_racekattekluben.application.interfaces.IExhibitionRepository;
import dk.zealand.hw_racekattekluben.domain.Exhibition;
import dk.zealand.hw_racekattekluben.domain.exceptions.ExhibitionNotFoundException;
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
        if (id <= 0) throw new IllegalArgumentException("Id skal være større end 0");
        Exhibition exhibition = exhibitionRepository.findById(id);
        if (exhibition == null) throw new ExhibitionNotFoundException(id);
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

    private void validateExhibition(Exhibition exhibition) {
        if (exhibition.getName() == null || exhibition.getName().isBlank())
            throw new IllegalArgumentException("Navn må ikke være tomt");
        if (exhibition.getLocation() == null || exhibition.getLocation().isBlank())
            throw new IllegalArgumentException("Lokation må ikke være tom");
        if (exhibition.getDate() == null)
            throw new IllegalArgumentException("Dato må ikke være tom");
    }

    public void delete(int id) {
        if (id <= 0) throw new IllegalArgumentException("Ugyldigt id");
        exhibitionRepository.delete(id);
    }

    public void registerCat(int catId, int exhibitionId) {
        if (catId <= 0) throw new IllegalArgumentException("Ugyldigt kat-id");
        if (exhibitionId <= 0) throw new IllegalArgumentException("Ugyldigt udstilling-id");
        exhibitionRepository.registerCat(catId, exhibitionId);
    }
}