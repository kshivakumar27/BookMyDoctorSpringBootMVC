package com.bookMyDoctor.model.viewModel;

import java.util.Date;

public class DoctorViewModel {
    private long id;

    private String firstName;

    private String lastName;

    private String telephoneNumber;
    
    private String description;

    private Date startPracticeDate;

    private String picturePath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getStartPracticeDate() {
        return startPracticeDate;
    }

    public void setStartPracticeDate(Date startPracticeDate) {
        this.startPracticeDate = startPracticeDate;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
