package com.bookMyDoctor.service;

import com.bookMyDoctor.entity.WeekSchedule;
import com.bookMyDoctor.model.bindingModel.EditWeekScheduleModel;


public interface WeekScheduleService {
    EditWeekScheduleModel getById(long id);

    WeekSchedule createDefault();

    void save(EditWeekScheduleModel editWeekScheduleModel);
}
