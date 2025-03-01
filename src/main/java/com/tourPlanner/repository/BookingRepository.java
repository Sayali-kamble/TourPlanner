package com.tourPlanner.repository;

import com.tourPlanner.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
	List<Booking> findByEmail(String email);
    List<Booking> findByTourId(String tourId);
}
