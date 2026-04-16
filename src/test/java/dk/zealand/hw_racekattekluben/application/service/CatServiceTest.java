package dk.zealand.hw_racekattekluben.application.service;

import dk.zealand.hw_racekattekluben.application.interfaces.ICatRepository;
import dk.zealand.hw_racekattekluben.domain.Cat;
import dk.zealand.hw_racekattekluben.domain.exceptions.CatNotFoundException;
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
class CatServiceTest {

    @Mock
    private ICatRepository catRepository;

    @InjectMocks
    private CatService catService;

    private Cat cat;

    @BeforeEach
    void setUp() {
        cat = new Cat("Muffin", LocalDate.of(2020,
                3, 15), "MCO ns 22", "Birgitte Hansen", 2);
    }

    @Test
    void getById_notFound_throwsCatNotFoundException() {
        when(catRepository.findById(1)).thenReturn(null);

        assertThrows(CatNotFoundException.class, () -> catService.getById(1));
    }

    @Test
    void getByMemberId_invalidId_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> catService.getByMemberId(0));
    }
    @Test
    void update_invalidCat_throwsException() {
        Cat invalidCat = new Cat(null, null, null, null, 1);

        assertThrows(IllegalArgumentException.class, () -> catService.update(invalidCat));
    }

    @Test
    void getById_validId_returnsCat() {
        when(catRepository.findById(1)).thenReturn(cat);
        Cat result = catService.getById(1);
        assertEquals(cat, result);
    }

    @Test
    void getById_invalidId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> catService.getById(0));
    }

    @Test
    void getAll_returnsAllCats() {
        when(catRepository.findAll()).thenReturn(List.of(cat));
        List<Cat> result = catService.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void getByMemberId_validId_returnsCats() {
        when(catRepository.findByMemberId(2)).thenReturn(List.of(cat));
        List<Cat> result = catService.getByMemberId(2);
        assertEquals(1, result.size());
    }

    @Test
    void create_withValidCat_savesCat() {
        catService.create(cat, "Birgitte Hansen");
        verify(catRepository).save(cat);
    }
}