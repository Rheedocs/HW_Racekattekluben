package dk.zealand.hw_racekattekluben.application.service;

import dk.zealand.hw_racekattekluben.application.interfaces.IExhibitionRepository;
import dk.zealand.hw_racekattekluben.domain.Exhibition;
import dk.zealand.hw_racekattekluben.domain.exceptions.ExhibitionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExhibitionServiceTest {

    @Mock
    private IExhibitionRepository exhibitionRepository;

    @InjectMocks
    private ExhibitionService exhibitionService;

    private Exhibition exhibition;

    @BeforeEach
    void setUp() {
        exhibition = new Exhibition(1, "Dansk Racekat Show 2024", "Odense Congress Center",
                LocalDate.of(2024, 3, 10));
    }

    @Test
    void getById_validId_returnsExhibition() {
        when(exhibitionRepository.findById(1)).thenReturn(exhibition);
        Exhibition result = exhibitionService.getById(1);
        assertEquals(exhibition, result);
    }

    @Test
    void getById_invalidId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> exhibitionService.getById(0));
    }

    @Test
    void getById_notFound_throwsExhibitionNotFoundException() {
        when(exhibitionRepository.findById(99)).thenReturn(null);
        assertThrows(ExhibitionNotFoundException.class, () -> exhibitionService.getById(99));
    }

    @Test
    void getAll_returnsAllExhibitions() {
        when(exhibitionRepository.findAll()).thenReturn(List.of(exhibition));
        List<Exhibition> result = exhibitionService.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void create_withValidExhibition_savesExhibition() {
        exhibitionService.create(exhibition);
        verify(exhibitionRepository).save(exhibition);
    }

    @Test
    void create_withInvalidExhibition_throwsIllegalArgumentException() {
        Exhibition invalid = new Exhibition(0, null, null, null);
        assertThrows(IllegalArgumentException.class, () -> exhibitionService.create(invalid));
    }

    @Test
    void delete_withValidId_deletesExhibition() {
        exhibitionService.delete(1);
        verify(exhibitionRepository).delete(1);
    }

    @Test
    void delete_withInvalidId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> exhibitionService.delete(0));
    }
}