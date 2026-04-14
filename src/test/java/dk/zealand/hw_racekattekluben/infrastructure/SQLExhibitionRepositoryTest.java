package dk.zealand.hw_racekattekluben.infrastructure;

import dk.zealand.hw_racekattekluben.domain.Exhibition;
import dk.zealand.hw_racekattekluben.infrastructure.mappers.ExhibitionRowMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import({SQLExhibitionRepository.class, ExhibitionRowMapper.class})
class SQLExhibitionRepositoryTest {

    @Autowired
    private SQLExhibitionRepository repository;

    @Test
    void findAll_returnsAllExhibitions() {
        List<Exhibition> exhibitions = repository.findAll();
        assertEquals(3, exhibitions.size());
    }

    @Test
    void findById_existingId_returnsExhibition() {
        Exhibition exhibition = repository.findById(1);
        assertNotNull(exhibition);
        assertEquals("Dansk Racekat Show 2024", exhibition.getName());
    }

    @Test
    void findById_nonExistingId_returnsNull() {
        Exhibition exhibition = repository.findById(999);
        assertNull(exhibition);
    }

    @Test
    void save_newExhibition_canBeFoundAfterwards() {
        Exhibition exhibition = new Exhibition(0, "Test Show", "Test Location",
                LocalDate.of(2025, 6, 1));
        repository.save(exhibition);

        List<Exhibition> all = repository.findAll();
        boolean found = false;
        for (Exhibition e : all) {
            if (e.getName().equals("Test Show")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    void update_existingExhibition_updatesCorrectly() {
        Exhibition exhibition = repository.findById(1);
        exhibition.setName("Opdateret Show");
        repository.update(exhibition);

        Exhibition updated = repository.findById(1);
        assertEquals("Opdateret Show", updated.getName());
    }

    @Test
    void delete_existingExhibition_cannotBeFoundAfterwards() {
        repository.delete(1);
        Exhibition deleted = repository.findById(1);
        assertNull(deleted);
    }

    @Test
    void registerCat_newRegistration_canBeFound() {
        repository.registerCat(1, 3);
        List<Integer> registeredIds = repository.findRegisteredCatIds(3);
        assertTrue(registeredIds.contains(1));
    }
}