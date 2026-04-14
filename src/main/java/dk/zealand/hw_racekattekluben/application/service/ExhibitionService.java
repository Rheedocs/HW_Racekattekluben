package dk.zealand.hw_racekattekluben.application.service;

import dk.zealand.hw_racekattekluben.application.interfaces.IExhibitionRepository;
import dk.zealand.hw_racekattekluben.domain.Cat;
import dk.zealand.hw_racekattekluben.domain.CatExhibitionEntry;
import dk.zealand.hw_racekattekluben.domain.Exhibition;
import dk.zealand.hw_racekattekluben.domain.ExhibitionDetailView;
import dk.zealand.hw_racekattekluben.domain.exceptions.ExhibitionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ExhibitionService {

    private final IExhibitionRepository exhibitionRepository;
    private final CatService catService;

    public ExhibitionService(IExhibitionRepository exhibitionRepository, CatService catService) {
        this.exhibitionRepository = exhibitionRepository;
        this.catService = catService;
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

    /**
     * Henter en samlet visning af en udstilling med tilmeldte katte
     * og tilgængelige katte for den loggede bruger.
     * Returnerer tom liste af tilgængelige katte hvis ingen bruger er logget ind.
     *
     * @param exhibitionId udstillingens id
     * @param memberId den loggede brugers id, eller 0 hvis ikke logget ind
     * @return ExhibitionDetailView med udstilling, tilmeldte katte og tilgængelige katte
     */
    public ExhibitionDetailView getDetailView(int exhibitionId, int memberId) {
        Exhibition exhibition = getById(exhibitionId);
        List<CatExhibitionEntry> entries = exhibitionRepository.findCatsByExhibitionId(exhibitionId);
        List<Integer> registeredIds = exhibitionRepository.findRegisteredCatIds(exhibitionId);
        List<Cat> availableCats = memberId > 0
                ? catService.filterAvailable(catService.getByMemberId(memberId), registeredIds)
                : Collections.emptyList();
        return new ExhibitionDetailView(exhibition, entries, availableCats);
    }

    public void create(Exhibition exhibition) {
        validateExhibition(exhibition);
        exhibitionRepository.save(exhibition);
    }

    public void update(Exhibition exhibition) {
        validateExhibition(exhibition);
        exhibitionRepository.update(exhibition);
    }

    public void delete(int id) {
        if (id <= 0) throw new IllegalArgumentException("Ugyldigt id");
        exhibitionRepository.delete(id);
    }

    /**
     * Registrerer en kat til en udstilling.
     * Validerer at kat og udstilling har gyldige id'er,
     * at katten tilhører den loggede bruger,
     * og at katten ikke allerede er tilmeldt udstillingen.
     *
     * @param catId kattens id
     * @param exhibitionId udstillingens id
     * @param loggedInMemberId den loggede brugers id
     */
    public void registerCat(int catId, int exhibitionId, int loggedInMemberId) {
        if (catId <= 0) throw new IllegalArgumentException("Ugyldigt kat-id");
        if (exhibitionId <= 0) throw new IllegalArgumentException("Ugyldigt udstilling-id");
        Cat cat = catService.getById(catId);
        if (cat.getMemberId() != loggedInMemberId)
            throw new IllegalArgumentException("Du kan kun tilmelde dine egne katte");
        if (exhibitionRepository.findRegisteredCatIds(exhibitionId).contains(catId))
            throw new IllegalArgumentException("Katten er allerede tilmeldt denne udstilling");
        exhibitionRepository.registerCat(catId, exhibitionId);
    }

    private void validateExhibition(Exhibition exhibition) {
        if (exhibition.getName() == null || exhibition.getName().isBlank())
            throw new IllegalArgumentException("Navn må ikke være tomt");
        if (exhibition.getLocation() == null || exhibition.getLocation().isBlank())
            throw new IllegalArgumentException("Lokation må ikke være tom");
        if (exhibition.getDate() == null)
            throw new IllegalArgumentException("Dato må ikke være tom");
    }
}