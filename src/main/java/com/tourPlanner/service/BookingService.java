package com.tourPlanner.service;

import com.tourPlanner.dto.BookingDTO;
import com.tourPlanner.entity.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    Booking createBooking(BookingDTO bookingDTO);
    
    List<Booking> getAllBookings();
    
    List<Booking> getBookingsByTourId(String tourId);
    
    Optional<Booking> getBookingById(String id);
    
    void deleteBooking(String id);
}

