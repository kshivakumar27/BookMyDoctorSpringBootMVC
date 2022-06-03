package com.bookMyDoctor.service;

import com.bookMyDoctor.entity.Patient;
import com.bookMyDoctor.model.bindingModel.EditPatientModel;
import com.bookMyDoctor.model.bindingModel.PatientRegistrationModel;
import com.bookMyDoctor.model.viewModel.PatientBasicViewModel;
import com.bookMyDoctor.model.viewModel.PatientViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    void create(PatientRegistrationModel registrationModel);

    void save(EditPatientModel editPatientModel);

    PatientViewModel getById(long id);

    Patient getByUserId(long userId);

    EditPatientModel getEditModelByUserId(long userId);

    PatientBasicViewModel getBasicById(long id);

    List<PatientBasicViewModel> getPatientsByDoctorId(long doctorId);

    Page<PatientViewModel> getPatientsByDoctorId(Pageable pageable, long doctorId);

    Page<PatientViewModel> getAll(Pageable pageable);
}
