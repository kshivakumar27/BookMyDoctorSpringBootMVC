package com.bookMyDoctor.model.bindingModel;

import java.util.HashSet;
import java.util.Set;

public class WeekScheduleModel {
    private Set<DayScheduleModel> dayScheduleModels;

    public WeekScheduleModel() {
        this.setDayScheduleModels(new HashSet<>());
    }

    public Set<DayScheduleModel> getDayScheduleModels() {
        return dayScheduleModels;
    }

    public void setDayScheduleModels(Set<DayScheduleModel> dayScheduleModels) {
        this.dayScheduleModels = dayScheduleModels;
    }
}
