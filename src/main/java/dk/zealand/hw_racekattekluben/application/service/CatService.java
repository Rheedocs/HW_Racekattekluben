package dk.zealand.hw_racekattekluben.application.service;

import dk.zealand.hw_racekattekluben.application.interfaces.ICatRepository;
import dk.zealand.hw_racekattekluben.domain.Cat;
import dk.zealand.hw_racekattekluben.domain.exceptions.CatNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatService {

    private final ICatRepository catRepository;

    public CatService(ICatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public List<Cat> getAll() {
        return catRepository.findAll();
    }

    public Cat getById(int id) {
        if (id <= 0) throw new IllegalArgumentException("Id skal være større end 0");
        Cat cat = catRepository.findById(id);
        if (cat == null) throw new CatNotFoundException(id);
        return cat;
    }

    /**
     * Filtrerer en liste af katte så kun dem der ikke allerede
     * er tilmeldt den givne udstilling returneres.
     *
     * @param myCats liste af katte tilhørende den loggede bruger
     * @param registeredIds id'er på katte der allerede er tilmeldt
     * @return liste af katte der kan tilmeldes
     */
    public List<Cat> filterAvailable(List<Cat> myCats, List<Integer> registeredIds) {
        List<Cat> available = new ArrayList<>();
        for (Cat cat : myCats) if (!registeredIds.contains(cat.getId())) available.add(cat);
        return available;
    }

    public List<Cat> getByMemberId(int memberId) {
        if (memberId <= 0) throw new IllegalArgumentException("Member id skal være større end 0");
        return catRepository.findByMemberId(memberId);
    }

    public void create(Cat cat, String memberName) {
        cat.setBreederName(memberName);
        validateCat(cat);
        catRepository.save(cat);
    }

    public void update(Cat cat) {
        Cat existing = getById(cat.getId());
        cat.setBreederName(existing.getBreederName());
        validateCat(cat);
        catRepository.update(cat);
    }

    public void delete(int id) {
        if (id <= 0) throw new IllegalArgumentException("Id skal være større end 0");
        catRepository.delete(id);
    }

    private void validateCat(Cat cat) {
        if (cat.getName() == null || cat.getName().isBlank())
            throw new IllegalArgumentException("Navn må ikke være tomt");
        if (cat.getBirthdate() == null)
            throw new IllegalArgumentException("Fødselsdato må ikke være tom");
        if (cat.getMotherId() != null && cat.getMotherId().equals(cat.getFatherId()))
            throw new IllegalArgumentException("Mor og far kan ikke være den samme kat");
        if (cat.getMotherId() != null && cat.getMotherId() == cat.getId())
            throw new IllegalArgumentException("En kat kan ikke være sin egen mor");
        if (cat.getFatherId() != null && cat.getFatherId() == cat.getId())
            throw new IllegalArgumentException("En kat kan ikke være sin egen far");
    }
}