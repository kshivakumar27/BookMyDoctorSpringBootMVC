package com.bookMyDoctor.service;

import com.bookMyDoctor.model.bindingModel.AddAppointmentModel;
import com.bookMyDoctor.model.viewModel.AppointmentDateViewModel;
import com.bookMyDoctor.model.viewModel.AppointmentViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface AppointmentService {
    void save(AddAppointmentModel addAppointmentModel);

    List<AppointmentDateViewModel> getAllForDateAndDoctor(Date date, long doctorId);

    List<AppointmentViewModel> getAllForPatientById(long patientId);

    Page<AppointmentViewModel> getAllForPatientById(long patientId, Pageable pageable);

    List<AppointmentViewModel> getAllForDoctorById(long doctorId);

    Page<AppointmentViewModel> getAllForDoctorById(long doctorId, Pageable pageable);

    AppointmentViewModel getByDateAndDoctorId(Date date, long doctorId);

    AppointmentViewModel getById(long id);
}
