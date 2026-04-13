package dk.zealand.hw_racekattekluben.infrastructure;

import dk.zealand.hw_racekattekluben.domain.Cat;
import dk.zealand.hw_racekattekluben.infrastructure.mappers.CatRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import({SQLCatRepository.class, CatRowMapper.class})
class SQLCatRepositoryTest {

    @Autowired
    private SQLCatRepository repository;

    @Test
    void findAll_returnsAllCats() {
        List<Cat> allCats = repository.findAll();
        assertEquals(4, allCats.size());
    }

    @Test
    void findById_existingId_returnsCat() {
        Cat cat = repository.findById(1);
        assertNotNull(cat);
        assertEquals("Muffin", cat.getName());
    }

    @Test
    void findById_nonExistingId_returnsNull() {
        Cat cat = repository.findById(999);
        assertNull(cat);
    }

    @Test
    void findByMemberId_existingMemberId_returnsCats() {
        List<Cat> cats = repository.findByMemberId(2);
        assertFalse(cats.isEmpty());
        assertEquals("Muffin", cats.getFirst().getName());
    }

    @Test
    void findByMemberId_nonExistingMemberId_returnsEmptyList() {
        List<Cat> cats = repository.findByMemberId(9999);
        assertTrue(cats.isEmpty());
    }

    @Test
    void save_newCat_canBeFoundAfterwards() {
        Cat cat = new Cat("Test", LocalDate.of(2020, 3, 15), "MCO ns 22", "Test Breeder", 1);
        repository.save(cat);

        List<Cat> cats = repository.findByMemberId(1);
        assertTrue(cats.stream().anyMatch(c -> c.getName().equals("Test")));
    }

    @Test
    void update_existingCat_updatesCorrectly() {
        Cat cat = repository.findByMemberId(2).getFirst();
        cat.setName("Opdateret Navn");
        repository.update(cat);

        Cat updated = repository.findById(cat.getId());
        assertEquals("Opdateret Navn", updated.getName());
    }

    @Test
    void update_existingCat_updatesDeathdate() {
        Cat cat = repository.findByMemberId(2).getFirst();
        cat.markAsDead(LocalDate.of(2024, 1, 1));
        repository.update(cat);

        Cat updated = repository.findById(cat.getId());
        assertEquals(LocalDate.of(2024, 1, 1), updated.getDeathdate());
    }

    @Test
    void update_existingCat_updatesParents() {
        Cat cat = repository.findByMemberId(2).getFirst();
        cat.setParents(3, 2);
        repository.update(cat);

        Cat updated = repository.findById(cat.getId());
        assertEquals(3, updated.getMotherId());
        assertEquals(2, updated.getFatherId());
    }

    @Test
    void delete_existingCat_cannotBeFoundAfterwards() {
        repository.delete(1);
        Cat deleted = repository.findById(1);
        assertNull(deleted);
    }
}