package dk.zealand.hw_racekattekluben.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExhibitionTest {

    @Test
    void isUpcoming_withFutureDate_returnsTrue() {
        Exhibition exhibition = new Exhibition(0, "Test", "Test",
                LocalDate.now().plusDays(1));
        assertTrue(exhibition.isUpcoming());
    }

    @Test
    void isUpcoming_withPastDate_returnsFalse() {
        Exhibition exhibition = new Exhibition(0, "Test", "Test",
                LocalDate.now().minusDays(1));
        assertFalse(exhibition.isUpcoming());
    }
}