package com.bookMyDoctor.serviceImpl;

import com.bookMyDoctor.entity.Doctor;
import com.bookMyDoctor.entity.Patient;
import com.bookMyDoctor.entity.User;
import com.bookMyDoctor.model.bindingModel.EditPatientModel;
import com.bookMyDoctor.model.bindingModel.PatientRegistrationModel;
import com.bookMyDoctor.model.bindingModel.UserRegistrationModel;
import com.bookMyDoctor.model.viewModel.PatientBasicViewModel;
import com.bookMyDoctor.model.viewModel.PatientViewModel;
import com.bookMyDoctor.repository.PatientRepository;
import com.bookMyDoctor.service.DoctorService;
import com.bookMyDoctor.service.PatientService;
import com.bookMyDoctor.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;

    private ModelMapper modelMapper;

    private UserService userService;

    private DoctorService doctorService;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper,
                              UserService userService, DoctorService doctorService) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.doctorService = doctorService;
    }

    @Override
    public void create(PatientRegistrationModel registrationModel) {
        UserRegistrationModel userRegistrationModel = this.modelMapper.map(registrationModel, UserRegistrationModel.class);
        String DEFAULT_PATIENT_ROLE = "ROLE_PATIENT";
        userRegistrationModel.setAdditionalRole(DEFAULT_PATIENT_ROLE);
        User user = this.userService.register(userRegistrationModel);

        Doctor doctor = this.doctorService.getById(registrationModel.getDoctor());
        Patient patient = this.modelMapper.map(registrationModel, Patient.class);
       
        patient.setUser(user);
        patient.setDoctor(doctor);

        this.patientRepository.saveAndFlush(patient);
    }

    @Override
    public void save(EditPatientModel editPatientModel) {
        Patient currentPatient = this.patientRepository.findOne(editPatientModel.getId());
        Patient patient = this.modelMapper.map(editPatientModel, Patient.class);

        
        patient.setUser(currentPatient.getUser());
        patient.setDoctor(currentPatient.getDoctor());
        patient.setDateOfEnrollment(currentPatient.getDateOfEnrollment());

        this.patientRepository.saveAndFlush(patient);
    }

    @Override
    public PatientViewModel getById(long id) {
        Patient patient = this.patientRepository.findOne(id);

        return this.modelMapper.map(patient, PatientViewModel.class);
    }

    @Override
    public Patient getByUserId(long userId) {
        return this.patientRepository.findOneByUserId(userId);
    }

    @Override
    public EditPatientModel getEditModelByUserId(long userId) {
        Patient patient = this.patientRepository.findOneByUserId(userId);

        return this.modelMapper.map(patient, EditPatientModel.class);
    }

    @Override
    public PatientBasicViewModel getBasicById(long id) {
        Patient patient = this.patientRepository.findOne(id);

        return this.modelMapper.map(patient, PatientBasicViewModel.class);
    }

    @Override
    public List<PatientBasicViewModel> getPatientsByDoctorId(long doctorId) {
        List<Patient> patients = this.patientRepository.findAllByDoctorId(doctorId);
        List<PatientBasicViewModel> patientBasicViewModels = new ArrayList<>();
        for (Patient patient : patients) {
            PatientBasicViewModel patientBasicViewModel = this.modelMapper.map(patient, PatientBasicViewModel.class);
            patientBasicViewModels.add(patientBasicViewModel);
        }

        return patientBasicViewModels;
    }

    @Override
    public Page<PatientViewModel> getPatientsByDoctorId(Pageable pageable, long doctorId) {
        Page<Patient> patients = this.patientRepository.findAllByDoctorIdOrderByDateOfBirthDesc(doctorId, pageable);
        List<PatientViewModel> patientViewModels = new ArrayList<>();
        for (Patient patient : patients) {
            PatientViewModel patientViewModel = this.modelMapper.map(patient, PatientViewModel.class);
            patientViewModels.add(patientViewModel);
        }

        return (Page<PatientViewModel>) new PageImpl(patientViewModels, pageable, patients.getTotalElements());
    }

    @Override
    public Page<PatientViewModel> getAll(Pageable pageable) {
        Page<Patient> patients = this.patientRepository.findAll(pageable);
        List<PatientViewModel> patientViewModels = new ArrayList<>();
        for (Patient patient : patients) {
            PatientViewModel patientViewModel = this.modelMapper.map(patient, PatientViewModel.class);
            patientViewModels.add(patientViewModel);
        }

        return (Page<PatientViewModel>) new PageImpl(patientViewModels, pageable, patients.getTotalElements());
    }
}
