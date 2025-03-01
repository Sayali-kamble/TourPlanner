package com.tourPlanner.service;

import com.tourPlanner.dto.BookingDTO;
import com.tourPlanner.entity.Booking;
import com.tourPlanner.repository.BookingRepository;
import com.tourPlanner.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking(
                null,
                bookingDTO.getTourId(),
                bookingDTO.getTourTitle(),
                bookingDTO.getFullName(),
                bookingDTO.getEmail(),
                bookingDTO.getNumberOfPeople(),
                bookingDTO.getBookingDate()
        );
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getBookingsByTourId(String tourId) {
        return bookingRepository.findByTourId(tourId);
    }

    @Override
    public Optional<Booking> getBookingById(String id) {
        return bookingRepository.findById(id);
    }

    @Override
    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }
}
