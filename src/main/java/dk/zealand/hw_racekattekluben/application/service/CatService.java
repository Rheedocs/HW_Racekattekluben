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

    public List<Cat> getByMemberId(int memberId) {
        if (memberId <= 0) throw new IllegalArgumentException("Member id skal være større end 0");
        return catRepository.findByMemberId(memberId);
    }

    public void create(Cat cat) {
        validateCat(cat);
        catRepository.save(cat);
    }

    public void update(Cat cat) {
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
        if (cat.getBreederName() == null || cat.getBreederName().isBlank())
            throw new IllegalArgumentException("Opdrætter navn må ikke være tomt");
        if (cat.getBirthdate() == null)
            throw new IllegalArgumentException("Fødselsdato må ikke være tom");
    }
}