package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllStocks() {
        // Mock the behavior of the repository
        when(stockRepository.findAll()).thenReturn(new ArrayList<>());

        // Call the method being tested
        List<Stock> result = stockService.retrieveAllStocks();

        // Assert the result
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testAddStock() {
        // Create a sample Stock object
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setLibelleStock("Test Stock1");

        // Mock the behavior of the repository
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);

        // Call the method being tested
        Stock result = stockService.addStock(stock);

        // Assert the result
        assertNotNull(result);
        assertEquals(stock.getIdStock(), result.getIdStock());
        assertEquals(stock.getLibelleStock(), result.getLibelleStock());

        verify(stockRepository, times(1)).save(any(Stock.class));
    }

    @Test
    void testDeleteStock() {
        // Create a sample Stock ID
        Long stockId = 1L;

        // Call the method being tested
        stockService.deleteStock(stockId);

        // Verify that the repository's deleteById method was called with the correct ID
        verify(stockRepository, times(1)).deleteById(stockId);
    }

    @Test
    void testUpdateStock() {
        // Create a sample Stock object
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setLibelleStock("Test Stock");

        // Mock the behavior of the repository
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);

        // Call the method being tested
        Stock result = stockService.updateStock(stock);

        // Assert the result
        assertNotNull(result);
        assertEquals(stock.getIdStock(), result.getIdStock());
        assertEquals(stock.getLibelleStock(), result.getLibelleStock());

        verify(stockRepository, times(1)).save(any(Stock.class));
    }

    @Test
    void testRetrieveStock() {
        // Create a sample Stock ID
        Long stockId = 1L;

        // Create a sample Stock object
        Stock stock = new Stock();
        stock.setIdStock(stockId);
        stock.setLibelleStock("Test Stock");

        // Mock the behavior of the repository
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        // Call the method being tested
        Stock result = stockService.retrieveStock(stockId);

        // Assert the result
        assertNotNull(result);
        assertEquals(stockId, result.getIdStock());
        assertEquals(stock.getLibelleStock(), result.getLibelleStock());
    }

    @Test
    void testRetrieveStatusStock() {
        // Mock the behavior of the repository
        when(stockRepository.retrieveStatusStock()).thenReturn(new ArrayList<>());

        // Call the method being tested
        String result = stockService.retrieveStatusStock();

        // Assert the result
        assertNotNull(result);
        assertEquals("", result);
    }
}
