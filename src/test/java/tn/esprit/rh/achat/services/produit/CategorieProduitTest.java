package produit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.controllers.CategorieProduitController;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.services.ICategorieProduitService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategorieProduitTest {

    @Mock
    private ICategorieProduitService categorieProduitService;

    @InjectMocks
    private CategorieProduitController categorieProduitController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCategorieProduit() {
        // Mock des données de test
        List<CategorieProduit> categorieProduitList = new ArrayList<>();
        categorieProduitList.add(new CategorieProduit(1L, "Catégorie 1","lib 1"));
        categorieProduitList.add(new CategorieProduit(2L, "Catégorie 2","lib 2"));

        // Définir le comportement du mock
        when(categorieProduitService.retrieveAllCategorieProduits()).thenReturn(categorieProduitList);

        // Appeler la méthode à tester
        List<CategorieProduit> result = categorieProduitController.getCategorieProduit();

        // Vérifier le résultat
        assertEquals(categorieProduitList, result);
        verify(categorieProduitService, times(1)).retrieveAllCategorieProduits();
    }

    @Test
    void testRetrieveCategorieProduit() {
        // Mock des données de test
        Long categorieProduitId = 1L;
        CategorieProduit categorieProduit = new CategorieProduit(categorieProduitId, "Catégorie 1","lib 1");

        // Définir le comportement du mock
        when(categorieProduitService.retrieveCategorieProduit(categorieProduitId)).thenReturn(categorieProduit);

        // Appeler la méthode à tester
        CategorieProduit result = categorieProduitController.retrieveCategorieProduit(categorieProduitId);

        // Vérifier le résultat
        assertEquals(categorieProduit, result);
        verify(categorieProduitService, times(1)).retrieveCategorieProduit(categorieProduitId);
    }

    // Écrivez des tests similaires pour les autres méthodes du contrôleur

    // ...

}
