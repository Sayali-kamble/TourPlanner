package com.tourPlanner.service;

import com.tourPlanner.dto.BookingDTO;
import com.tourPlanner.entity.Booking;
import com.tourPlanner.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private Booking booking;
    private BookingDTO bookingDTO;

    @BeforeEach
    void setUp() {
        booking = new Booking(
                "B001",
                "T123",
                "Amazing Tour",
                "John Doe",
                "john@example.com",
                2,
                "2025-03-15"
        );

        bookingDTO = new BookingDTO(
                "T123",
                "Amazing Tour",
                "John Doe",
                "john@example.com",
                null, 2,
                "2025-03-15"
        );
    }

    @Test
    void testCreateBooking() {
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking savedBooking = bookingService.createBooking(bookingDTO);

        assertNotNull(savedBooking);
        assertEquals("B001", savedBooking.getId());
        assertEquals("T123", savedBooking.getTourId());
        assertEquals("Amazing Tour", savedBooking.getTourTitle());

        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void testGetAllBookings() {
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking));

        List<Booking> bookings = bookingService.getAllBookings();

        assertFalse(bookings.isEmpty());
        assertEquals(1, bookings.size());
        assertEquals("John Doe", bookings.get(0).getFullName());

        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void testGetBookingsByTourId() {
        when(bookingRepository.findByTourId("T123")).thenReturn(Arrays.asList(booking));

        List<Booking> bookings = bookingService.getBookingsByTourId("T123");

        assertFalse(bookings.isEmpty());
        assertEquals(1, bookings.size());
        assertEquals("T123", bookings.get(0).getTourId());

        verify(bookingRepository, times(1)).findByTourId("T123");
    }

    @Test
    void testGetBookingById_Found() {
        when(bookingRepository.findById("B001")).thenReturn(Optional.of(booking));

        Optional<Booking> foundBooking = bookingService.getBookingById("B001");

        assertTrue(foundBooking.isPresent());
        assertEquals("B001", foundBooking.get().getId());

        verify(bookingRepository, times(1)).findById("B001");
    }

    @Test
    void testGetBookingById_NotFound() {
        when(bookingRepository.findById("B999")).thenReturn(Optional.empty());

        Optional<Booking> foundBooking = bookingService.getBookingById("B999");

        assertFalse(foundBooking.isPresent());

        verify(bookingRepository, times(1)).findById("B999");
    }

    @Test
    void testDeleteBooking() {
        doNothing().when(bookingRepository).deleteById("B001");

        bookingService.deleteBooking("B001");

        verify(bookingRepository, times(1)).deleteById("B001");
    }
}
