package dk.zealand.hw_racekattekluben.application.service;

import dk.zealand.hw_racekattekluben.application.interfaces.ICatRepository;
import dk.zealand.hw_racekattekluben.domain.Cat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {

    private final ICatRepository catRepository;

    public CatService(ICatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public List<Cat> getAll() {
        return null;
    }

    public Cat getById(int id) {
        return null;
    }

    public List<Cat> getByMemberId(int memberId) {
        return null;
    }

    public void create(Cat cat) {
    }

    public void update(Cat cat) {
    }

    public void delete(int id) {
    }
}