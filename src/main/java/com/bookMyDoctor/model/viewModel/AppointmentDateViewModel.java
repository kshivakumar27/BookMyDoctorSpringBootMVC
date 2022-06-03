package com.bookMyDoctor.model.viewModel;

import java.util.Date;

public class AppointmentDateViewModel {
    private long id;

    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
