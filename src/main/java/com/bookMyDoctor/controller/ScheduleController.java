package com.bookMyDoctor.controller;

import com.bookMyDoctor.entity.Doctor;
import com.bookMyDoctor.entity.Patient;
import com.bookMyDoctor.entity.User;
import com.bookMyDoctor.model.bindingModel.EditWeekScheduleModel;
import com.bookMyDoctor.model.viewModel.DoctorSelectViewModel;
import com.bookMyDoctor.service.DoctorService;
import com.bookMyDoctor.service.PatientService;
import com.bookMyDoctor.service.WeekScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    private WeekScheduleService weekScheduleService;

    private DoctorService doctorService;

    private PatientService patientService;

    @Autowired
    public ScheduleController(WeekScheduleService weekScheduleService, DoctorService doctorService,
                              PatientService patientService) {
        this.weekScheduleService = weekScheduleService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR')")
    @GetMapping("/")
    public String getSchedule(Authentication principal, Model model, HttpServletRequest request) {
        long userId = ((User) principal.getPrincipal()).getId();
        if (request.isUserInRole("ROLE_DOCTOR")) {
            DoctorSelectViewModel doctor = this.doctorService.getModelByUserId(userId);
            model.addAttribute("doctor", doctor);
        } else if (request.isUserInRole("ROLE_PATIENT")) {
            Patient patient = this.patientService.getByUserId(userId);
            DoctorSelectViewModel doctor = this.doctorService.getModelByUserId(patient.getDoctor().getUser().getId());
            model.addAttribute("doctor", doctor);
        }

        return "schedule/schedule";
    }

    @GetMapping("/edit")
    public String getEditSchedule(Principal principal, Model model, HttpServletRequest request) {
        long weekScheduleId = getWeekScheduleId((Authentication) principal, request);
        EditWeekScheduleModel editWeekScheduleModel = this.weekScheduleService.getById(weekScheduleId);

        model.addAttribute("editWeekScheduleModel", editWeekScheduleModel);

        return "schedule/edit";
    }

    @PostMapping("/edit")
    public String editSchedule(@Valid @ModelAttribute EditWeekScheduleModel editWeekScheduleModel,
                               BindingResult bindingResult, Principal principal, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "schedule/edit";
        }

        long weekScheduleId = getWeekScheduleId((Authentication) principal, request);

        editWeekScheduleModel.setId(weekScheduleId);
        EditWeekScheduleModel editWeekScheduleModelIds = this.weekScheduleService.getById(weekScheduleId);
        for (int i = 0; i < editWeekScheduleModel.getEditDayScheduleModels().size(); i++) {
            editWeekScheduleModel
                    .getEditDayScheduleModels()
                    .get(i).setId(editWeekScheduleModelIds
                    .getEditDayScheduleModels().get(i).getId());
        }

        this.weekScheduleService.save(editWeekScheduleModel);

        return "redirect:/schedule/";
    }

    @GetMapping("/week")
    public ResponseEntity<EditWeekScheduleModel> getWeekSchedule(Principal principal, HttpServletRequest request) {
        long weekScheduleId = getWeekScheduleId((Authentication) principal, request);
        EditWeekScheduleModel editWeekScheduleModel = this.weekScheduleService.getById(weekScheduleId);

        return ResponseEntity.ok(editWeekScheduleModel);
    }

    private long getWeekScheduleId(Authentication principal, HttpServletRequest request) {
        long userId = ((User) principal.getPrincipal()).getId();
        if (request.isUserInRole("ROLE_DOCTOR")) {
            Doctor doctor = this.doctorService.getByUserId(userId);
            return doctor.getWeekSchedule().getId();
        } else if (request.isUserInRole("ROLE_PATIENT")) {
            Patient patient = this.patientService.getByUserId(userId);
            return patient.getDoctor().getWeekSchedule().getId();
        }

        return 0;
    }
}
