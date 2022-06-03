package com.bookMyDoctor.model.bindingModel;

public class EditDoctorPictureModel {
    private long id;

    private String picturePath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
