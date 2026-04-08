package dk.zealand.hw_racekattekluben.domain;

import dk.zealand.hw_racekattekluben.domain.exceptions.CatAlreadyDeadException;
import dk.zealand.hw_racekattekluben.domain.exceptions.InvalidDeathdateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CatTest {

    private Cat cat;

    @BeforeEach
    void setUp() {
        cat = new Cat("Muffin", LocalDate.of(2020,
                3, 15), "MCO ns 22", "Birgitte Hansen", 2);
    }

    @Test
    void markAsDead_withValidDate_setsDeathdateCorrectly() {
        LocalDate deathdate = LocalDate.of(2024, 1, 5);
        cat.markAsDead(deathdate);
        assertEquals(deathdate, cat.getDeathdate());
    }

    @Test
    void markAsDead_whenAlreadyDead_throwsCatAlreadyDeadException() {
        cat.markAsDead(LocalDate.of(2024, 1, 5));
        assertThrows(CatAlreadyDeadException.class, () -> cat.markAsDead(LocalDate.of(2024,
                2, 1)));
    }

    @Test
    void markAsDead_withFutureDate_throwsInvalidDeathdateException() {
        assertThrows(InvalidDeathdateException.class, () -> cat.markAsDead(LocalDate.now().plusDays(1)));
    }

    @Test
    void setParents_withValidIds_setsMotherAndFatherCorrectly() {
        cat.setParents(3, 2);
        assertEquals(3, cat.getMotherId());
        assertEquals(2, cat.getFatherId());
    }

    @Test
    void setParents_withNullIds_setsParentsToNull() {
        cat.setParents(null, null);
        assertNull(cat.getMotherId());
        assertNull(cat.getFatherId());
    }
}