package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FournisseurServiceImplTest {

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllFournisseurs() {
        // Mock the behavior of the repository
        when(fournisseurRepository.findAll()).thenReturn(new ArrayList<>());

        // Call the method being tested
        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        // Assert the result
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testAddFournisseur() {
        // Create a sample Fournisseur object
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(1L);
        fournisseur.setNom("Test Fournisseur");

        // Create a sample DetailFournisseur object
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setDateDebutCollaboration(new Date());

        // Mock the behavior of the repository
        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(fournisseur);
        when(detailFournisseurRepository.save(any(DetailFournisseur.class))).thenReturn(detailFournisseur);

        // Call the method being tested
        Fournisseur result = fournisseurService.addFournisseur(fournisseur);

        // Assert the result
        assertNotNull(result);
        assertEquals(fournisseur.getId(), result.getId());
        assertEquals(fournisseur.getNom(), result.getNom());

        verify(fournisseurRepository, times(1)).save(any(Fournisseur.class));
        verify(detailFournisseurRepository, times(1)).save(any(DetailFournisseur.class));
    }

    @Test
    void testUpdateFournisseur() {
        // Create a sample Fournisseur object
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(1L);
        fournisseur.setNom("Test Fournisseur");

        // Create a sample DetailFournisseur object
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setId(1L);
        detailFournisseur.setDateDebutCollaboration(new Date());

        fournisseur.setDetailFournisseur(detailFournisseur);

        // Mock the behavior of the repository
        when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(fournisseur);
        when(detailFournisseurRepository.save(any(DetailFournisseur.class))).thenReturn(detailFournisseur);

        // Call the method being tested
        Fournisseur result = fournisseurService.updateFournisseur(fournisseur);

        // Assert the result
        assertNotNull(result);
        assertEquals(fournisseur.getId(), result.getId());
        assertEquals(fournisseur.getNom(), result.getNom());
        assertNotNull(result.getDetailFournisseur());
        assertEquals(detailFournisseur.getId(), result.getDetailFournisseur().getId());

        verify(fournisseurRepository, times(1)).save(any(Fournisseur.class));
        verify(detailFournisseurRepository, times(1)).save(any(DetailFournisseur.class));
    }

    @Test
    void testDeleteFournisseur() {
        // Create a sample Fournisseur ID
        Long fournisseurId = 1L;

        // Call the method being tested
        fournisseurService.deleteFournisseur(fournisseurId);

        // Verify that the repository's deleteById method was called with the correct ID
        verify(fournisseurRepository, times(1)).deleteById(fournisseurId);
    }

    @Test
    void testRetrieveFournisseur() {
        // Create a sample Fournisseur ID
        Long fournisseurId = 1L;

        // Create a sample Fournisseur object
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurId);
        fournisseur.setNom("Test Fournisseur");

        // Mock the behavior of the repository
        when(fournisseurRepository.findById(fournisseurId)).thenReturn(Optional.of(fournisseur));

        // Call the method being tested
        Fournisseur result = fournisseurService.retrieveFournisseur(fournisseurId);

        // Assert the result
        assertNotNull(result);
        assertEquals(fournisseurId, result.getId());
        assertEquals(fournisseur.getNom(), result.getNom());
    }

    @Test
    void testAssignSecteurActiviteToFournisseur() {
        // Create sample IDs for SecteurActivite and Fournisseur
        Long idSecteurActivite = 1L;
        Long idFournisseur = 1L;

        // Create sample objects for SecteurActivite and Fournisseur
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setId(idSecteurActivite);

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(idFournisseur);

        // Mock the behavior of the repository
        when(fournisseurRepository.findById(idFournisseur)).thenReturn(Optional.of(fournisseur));
        when(secteurActiviteRepository.findById(idSecteurActivite)).thenReturn(Optional.of(secteurActivite));

        // Call the method being tested
        fournisseurService.assignSecteurActiviteToFournisseur(idSecteurActivite, idFournisseur);

        // Verify that the SecteurActivite was added to the Fournisseur's list of SecteurActivites
        assertEquals(1, fournisseur.getSecteurActivites().size());
        assertEquals(secteurActivite, fournisseur.getSecteurActivites().get(0));

        // Verify that the changes were saved in the repository
        verify(fournisseurRepository, times(1)).save(fournisseur);
    }
}
