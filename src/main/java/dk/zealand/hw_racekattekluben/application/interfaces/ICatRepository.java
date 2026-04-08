package dk.zealand.hw_racekattekluben.application.interfaces;

import dk.zealand.hw_racekattekluben.domain.Cat;
import java.util.List;

public interface ICatRepository {
    List<Cat> findAll();
    Cat findById(int id);
    List<Cat> findByMemberId(int memberId);
    void save(Cat cat);
    void update(Cat cat);
    void delete(int id);
}