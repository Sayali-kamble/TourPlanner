package com.tourPlanner.repository;

import com.tourPlanner.entity.Tour;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TourRepository extends MongoRepository<Tour,String> {
    List<Tour> findByTitle(String title);
    void deleteByTitle(String title);
    
}
