package com.tourPlanner.service;

import com.tourPlanner.dto.TourDTO;
import com.tourPlanner.entity.Tour;
import com.tourPlanner.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {
	@Autowired
	private TourRepository tourRepository;

	@Override
	public List<TourDTO> findAll() {

		List<Tour> tempTourList = tourRepository.findAll();
		List<TourDTO> tempTourDTOList = new ArrayList<TourDTO>();
		for (Tour tempTour : tempTourList) {
			tempTourDTOList.add(new TourDTO(tempTour.getId(), tempTour.getTitle(), tempTour.getIncluded(),
					tempTour.getNotIncluded(), tempTour.getPrice(), tempTour.getHotel(), tempTour.getRoute(),
					tempTour.getTitle(), tempTour.getImg()));

		}
		return tempTourDTOList;
	}

	@Override
	public void saveTour(TourDTO tourDTO) {
		tourRepository.save(new Tour(tourDTO.getId(), tourDTO.getDuration(), tourDTO.getIncluded(),
				tourDTO.getNotIncluded(), tourDTO.getPrice(), tourDTO.getHotel(), tourDTO.getRoute(),
				tourDTO.getTitle(), tourDTO.getImg()));

	}

	@Override
	public TourDTO findTourByTitle(String title) {
	    List<Tour> tours = tourRepository.findByTitle(title);
	    
	    if (tours.isEmpty()) {
	        throw new RuntimeException("Tour not found with title: " + title);
	    }

	    Tour tempTour = tours.get(0);
	    return new TourDTO(tempTour.getId(), tempTour.getDuration(), tempTour.getIncluded(),
	            tempTour.getNotIncluded(), tempTour.getPrice(), tempTour.getHotel(),
	            tempTour.getRoute(), tempTour.getTitle(), tempTour.getImg());
	}


	@Override
	public TourDTO findTourById(String id) {
		Optional<Tour> optionalTour = tourRepository.findById(id);

		if (!optionalTour.isPresent()) {
			throw new RuntimeException("Tour not found with ID: " + id);
		}

		Tour tempTour = optionalTour.get();

		return new TourDTO(tempTour.getId(), tempTour.getTitle(), tempTour.getIncluded(), tempTour.getNotIncluded(),
				tempTour.getPrice(), tempTour.getHotel(), tempTour.getRoute(), tempTour.getTitle(), // Assuming a
																									// description field
																									// exists
				tempTour.getImg());
	}

	/*
	 * @Override public List<TourDTO> findAllTours(String id) { List<Tour>
	 * tempTourList = tourRepository.findAll(); List<TourDTO> tempTourDTOList = new
	 * ArrayList<TourDTO>(); for (Tour tempTour : tempTourList) {
	 * tempTourDTOList.add(new TourDTO(tempTour.getId(), tempTour.getDuration(),
	 * tempTour.getIncluded(), tempTour.getNotIncluded(), tempTour.getPrice(),
	 * tempTour.getHotel(), tempTour.getRoute(), tempTour.getTitle(),
	 * tempTour.getImg()));
	 * 
	 * } List<TourDTO> AllUserPayment = new ArrayList<TourDTO>(); for (TourDTO
	 * tempTourDTO : tempTourDTOList) { if (tempTourDTO.getId().equals(id)) {
	 * tempTourDTOList.add(tempTourDTO); } }
	 * 
	 * return tempTourDTOList; }
	 */
	
	@Override
	public List<TourDTO> findAllTours(String id) {
	    List<Tour> tempTourList = tourRepository.findAll();
	    List<TourDTO> tempTourDTOList = new ArrayList<>();

	    for (Tour tempTour : tempTourList) {
	        tempTourDTOList.add(new TourDTO(tempTour.getId(), tempTour.getDuration(), tempTour.getIncluded(),
	                tempTour.getNotIncluded(), tempTour.getPrice(), tempTour.getHotel(), tempTour.getRoute(),
	                tempTour.getTitle(), tempTour.getImg()));
	    }

	    List<TourDTO> filteredTours = new ArrayList<>();
	    for (TourDTO tempTourDTO : tempTourDTOList) {
	        if (tempTourDTO.getId().equals(id)) {
	            filteredTours.add(tempTourDTO);  // ✅ Correctly adding filtered results
	        }
	    }

	    return filteredTours;  // ✅ Return only the filtered list
	}


	@Override
	public void deleteTourByTitle(String title) {
		tourRepository.deleteByTitle(title);
	}
}
