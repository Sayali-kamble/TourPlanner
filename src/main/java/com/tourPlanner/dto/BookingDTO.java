package com.tourPlanner.dto;

public class BookingDTO {
    
    private String id;
    private String tourId;
    private String tourTitle;
    private String fullName;
    private String email;
    private int numberOfPeople;
    private String bookingDate;
    
    public BookingDTO() {
    }

    public BookingDTO(String id, String tourId, String tourTitle, String fullName, String email, int numberOfPeople, String bookingDate) {
        this.id = id;
        this.tourId = tourId;
        this.tourTitle = tourTitle;
        this.fullName = fullName;
        this.email = email;
        this.numberOfPeople = numberOfPeople;
        this.bookingDate = bookingDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getTourTitle() {
        return tourTitle;
    }

    public void setTourTitle(String tourTitle) {
        this.tourTitle = tourTitle;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id='" + id + '\'' +
                ", tourId='" + tourId + '\'' +
                ", tourTitle='" + tourTitle + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", numberOfPeople=" + numberOfPeople +
                ", bookingDate='" + bookingDate + '\'' +
                '}';
    }
}
