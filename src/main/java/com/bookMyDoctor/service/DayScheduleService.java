package com.bookMyDoctor.service;

import com.bookMyDoctor.model.bindingModel.DayScheduleModel;
import com.bookMyDoctor.model.bindingModel.EditDayScheduleModel;


public interface DayScheduleService {
    void create(DayScheduleModel dayScheduleModel);

    void save(EditDayScheduleModel editDayScheduleModel);
}
