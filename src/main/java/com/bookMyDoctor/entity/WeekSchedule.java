package com.bookMyDoctor.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name = "week_schedules")
public class WeekSchedule {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int appointmentDuration;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "weekSchedule")
    @OrderBy("id")
    private Set<DaySchedule> daySchedules;

    public WeekSchedule() {
        this.setDaySchedules(new HashSet<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAppointmentDuration() {
        return appointmentDuration;
    }

    public void setAppointmentDuration(int appointmentDuration) {
        this.appointmentDuration = appointmentDuration;
    }

    public Set<DaySchedule> getDaySchedules() {
        return daySchedules;
    }

    public void setDaySchedules(Set<DaySchedule> daySchedules) {
        this.daySchedules = daySchedules;
    }

}
