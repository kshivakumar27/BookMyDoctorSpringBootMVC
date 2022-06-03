package com.bookMyDoctor.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bookMyDoctor.entity.Doctor;
import com.bookMyDoctor.model.bindingModel.DoctorRegistrationModel;
import com.bookMyDoctor.model.bindingModel.EditDoctorModel;
import com.bookMyDoctor.model.bindingModel.EditDoctorPictureModel;
import com.bookMyDoctor.model.viewModel.DoctorSelectViewModel;
import com.bookMyDoctor.model.viewModel.DoctorViewModel;

public interface DoctorService {
	void create(DoctorRegistrationModel registrationModel);

    void save(EditDoctorModel editDoctorModel);

    void savePicture(EditDoctorPictureModel editDoctorPictureModel);

    Doctor getById(long id);

    DoctorViewModel getViewModelById(long id);

    EditDoctorPictureModel getPictureModelByUserId(long id);

    Doctor getByUserId(long userId);

    EditDoctorModel getEditModelByUserId(long userId);

    DoctorSelectViewModel getModelByUserId(long userId);

    List<DoctorSelectViewModel> getAllSelect();

    Page<DoctorViewModel> getAll(Pageable pageable);

}
