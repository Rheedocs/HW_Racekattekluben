package dk.zealand.hw_racekattekluben.application.service;

import dk.zealand.hw_racekattekluben.application.interfaces.ICatRepository;
import dk.zealand.hw_racekattekluben.domain.Cat;
import dk.zealand.hw_racekattekluben.domain.exceptions.CatNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {

    private final ICatRepository catRepository;

    public CatService(ICatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public List<Cat> getAll() { return catRepository.findAll();
    }

    public Cat getById(int id) {
        if (id <= 0){
            throw new IllegalArgumentException("Id skal være større end 0!");
        }
        Cat cat = catRepository.findById(id);
        if (cat == null) throw new CatNotFoundException(id);
        return cat;
    }

    public List<Cat> getByMemberId(int memberId) {
        if (memberId <= 0){throw new IllegalArgumentException("Member id skal være større end 0");}
        List<Cat> cats = catRepository.findByMemberId(memberId);
        if (cats == null || cats.isEmpty()){
            throw new IllegalArgumentException("Ingen katte blev fundet fo denne member id: " + memberId);
        }
        return cats;
    }

    public void catValidate(Cat cat){
        if (cat.getName() == null || cat.getBreederName() == null) {throw new IllegalArgumentException("Navn og Breeder navn må ikke være tomme");}
        if (cat.getDeathdate() == null || cat.getBirthdate() == null){throw new IllegalArgumentException("Death datoen og Birth datoen må ikke være tomme");}
    }

    public void create(Cat cat) {
        catValidate(cat);
        catRepository.save(cat);
    }

    public void update(Cat cat) {
        catValidate(cat);
        catRepository.update(cat);
    }

    public void delete(int id) {
        catRepository.delete(id);
    }
}