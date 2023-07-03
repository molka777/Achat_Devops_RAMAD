import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CategorieProduitServiceImplTest {

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllCategorieProduits() {
        // Prepare test data
        List<CategorieProduit> categorieProduits = new ArrayList<>();
        categorieProduits.add(new CategorieProduit(1L, "Category 1"));
        categorieProduits.add(new CategorieProduit(2L, "Category 2"));
        when(categorieProduitRepository.findAll()).thenReturn(categorieProduits);

        // Call the service method
        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Category 1", result.get(0).getName());
        assertEquals("Category 2", result.get(1).getName());
    }

    @Test
    public void testAddCategorieProduit() {
        // Prepare test data
        CategorieProduit cp = new CategorieProduit();
        cp.setName("New Category");

        // Call the service method
        CategorieProduit result = categorieProduitService.addCategorieProduit(cp);

        // Verify that the repository save method was called once
        verify(categorieProduitRepository, times(1)).save(cp);

        // Verify the result
        assertEquals("New Category", result.getName());
    }

}
