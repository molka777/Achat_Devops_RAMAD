package tn.esprit.rh.achat.services.produit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategorieProduitTest{

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllCategorieProduits() {
        List<CategorieProduit> categorieProduits = new ArrayList<>();
        categorieProduits.add(new CategorieProduit(1L, "Catégorie 1","lib 1"));
        categorieProduits.add(new CategorieProduit(2L, "Catégorie 2", "lib 2"));

        when(categorieProduitRepository.findAll()).thenReturn(categorieProduits);

        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();

        assertEquals(categorieProduits, result);
    }

    @Test
    public void testAddCategorieProduit() {
        CategorieProduit cp = new CategorieProduit(1L, "Catégorie 1","lib 1");

        when(categorieProduitRepository.save(cp)).thenReturn(cp);

        CategorieProduit result = categorieProduitService.addCategorieProduit(cp);

        assertEquals(cp, result);
        verify(categorieProduitRepository, times(1)).save(cp);
    }

    @Test
    public void testDeleteCategorieProduit() {
        Long id = 1L;

        categorieProduitService.deleteCategorieProduit(id);

        verify(categorieProduitRepository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdateCategorieProduit() {
        CategorieProduit cp = new CategorieProduit(1L, "Catégorie 1", "lib 1");

        when(categorieProduitRepository.save(cp)).thenReturn(cp);

        CategorieProduit result = categorieProduitService.updateCategorieProduit(cp);

        assertEquals(cp, result);
        verify(categorieProduitRepository, times(1)).save(cp);
    }

    @Test
    public void testRetrieveCategorieProduit() {
        Long id = 1L;
        CategorieProduit cp = new CategorieProduit(id, "Catégorie 1","lib 1");

        when(categorieProduitRepository.findById(id)).thenReturn(Optional.of(cp));

        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(id);

        assertEquals(cp, result);
    }
}
