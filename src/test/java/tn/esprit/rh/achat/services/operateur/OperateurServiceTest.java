package tn.esprit.rh.achat.services.operateur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class OperateurServiceTest {

	@Mock
	private OperateurRepository operateurRepository;

	@InjectMocks
	private OperateurServiceImpl operateurService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testRetrieveAllOperateurs() {
		// Prepare test data
		List<Operateur> operateurs = new ArrayList<>();
		operateurs.add(new Operateur(1L, "John", "Doe", "password", null));
		operateurs.add(new Operateur(2L, "Jane", "Smith", "password", null));

		// Mock the repository method
		when(operateurRepository.findAll()).thenReturn(operateurs);

		// Call the service method
		List<Operateur> result = operateurService.retrieveAllOperateurs();

		// Verify the repository method was called once
		verify(operateurRepository, times(1)).findAll();

		// Verify the result
		assertEquals(2, result.size());
		assertEquals("John", result.get(0).getNom());
		assertEquals("Jane", result.get(1).getNom());
	}

	@Test
	public void testAddOperateur() {
		// Prepare test data
		Operateur operateur = new Operateur(1L, "John", "Doe", "password", null);

		// Mock the repository method
		when(operateurRepository.save(operateur)).thenReturn(operateur);

		// Call the service method
		Operateur result = operateurService.addOperateur(operateur);

		// Verify the repository method was called once
		verify(operateurRepository, times(1)).save(operateur);

		// Verify the result
		assertNotNull(result);
		assertEquals("John", result.getNom());
	}

	@Test
	public void testDeleteOperateur() {
		// Prepare test data
		Long id = 1L;

		// Call the service method
		operateurService.deleteOperateur(id);

		// Verify the repository method was called once
		verify(operateurRepository, times(1)).deleteById(id);
	}

	@Test
	public void testUpdateOperateur() {
		// Prepare test data
		Operateur operateur = new Operateur(1L, "John", "Doe", "password", null);

		// Mock the repository method
		when(operateurRepository.save(operateur)).thenReturn(operateur);

		// Call the service method
		Operateur result = operateurService.updateOperateur(operateur);

		// Verify the repository method was called once
		verify(operateurRepository, times(1)).save(operateur);

		// Verify the result
		assertNotNull(result);
		assertEquals("John", result.getNom());
	}

	@Test
	public void testRetrieveOperateur() {
		// Prepare test data
		Long id = 1L;
		Operateur operateur = new Operateur(id, "John", "Doe", "password", null);

		// Mock the repository
	}
}