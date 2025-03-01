package com.tourPlanner.service;

import com.tourPlanner.dto.TourDTO;
import com.tourPlanner.entity.Tour;
import com.tourPlanner.repository.TourRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TourServiceImplTest {

    @Mock
    private TourRepository tourRepository;

    @InjectMocks
    private TourServiceImpl tourService;

    private Tour testTour;
    private TourDTO testTourDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testTour = new Tour("T123", "5 days", "Flights", "Lunch", 200.0, "Hotel ABC", "Route 66", "Amazing Tour", "image.jpg");
        testTourDTO = new TourDTO("T123", "5 days", "Flights", "Lunch", 200.0, "Hotel ABC", "Route 66", "Amazing Tour", "image.jpg");
    }

    @Test
    void testFindAll() {
        when(tourRepository.findAll()).thenReturn(Arrays.asList(testTour));

        List<TourDTO> result = tourService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(testTourDTO.getId(), result.get(0).getId());

        verify(tourRepository, times(1)).findAll();
    }

    @Test
    void testSaveTour() {
        when(tourRepository.save(any(Tour.class))).thenReturn(testTour);

        assertDoesNotThrow(() -> tourService.saveTour(testTourDTO));

        verify(tourRepository, times(1)).save(any(Tour.class));
    }

    @Test
    void testFindTourByTitle_Success() {
        when(tourRepository.findByTitle("Amazing Tour")).thenReturn(Arrays.asList(testTour));

        TourDTO result = tourService.findTourByTitle("Amazing Tour");

        assertNotNull(result);
        assertEquals(testTourDTO.getTitle(), result.getTitle());

        verify(tourRepository, times(1)).findByTitle("Amazing Tour");
    }

    @Test
    void testFindTourByTitle_NotFound() {
        when(tourRepository.findByTitle("Unknown")).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(RuntimeException.class, () -> tourService.findTourByTitle("Unknown"));
        assertEquals("Tour not found with title: Unknown", exception.getMessage());

        verify(tourRepository, times(1)).findByTitle("Unknown");
    }

    @Test
    void testFindTourById_Success() {
        when(tourRepository.findById("T123")).thenReturn(Optional.of(testTour));

        TourDTO result = tourService.findTourById("T123");

        assertNotNull(result);
        assertEquals("T123", result.getId());

        verify(tourRepository, times(1)).findById("T123");
    }

    @Test
    void testFindTourById_NotFound() {
        when(tourRepository.findById("T999")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> tourService.findTourById("T999"));
        assertEquals("Tour not found with ID: T999", exception.getMessage());

        verify(tourRepository, times(1)).findById("T999");
    }

    @Test
    void testFindAllTours_WithMatchingId() {
        when(tourRepository.findAll()).thenReturn(Arrays.asList(testTour));

        List<TourDTO> result = tourService.findAllTours("T123");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("T123", result.get(0).getId());

        verify(tourRepository, times(1)).findAll();
    }

    @Test
    void testFindAllTours_NoMatchingId() {
        when(tourRepository.findAll()).thenReturn(Arrays.asList(testTour));

        List<TourDTO> result = tourService.findAllTours("T999");

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(tourRepository, times(1)).findAll();
    }

    @Test
    void testDeleteTourByTitle() {
        doNothing().when(tourRepository).deleteByTitle("Amazing Tour");

        assertDoesNotThrow(() -> tourService.deleteTourByTitle("Amazing Tour"));

        verify(tourRepository, times(1)).deleteByTitle("Amazing Tour");
    }
}
