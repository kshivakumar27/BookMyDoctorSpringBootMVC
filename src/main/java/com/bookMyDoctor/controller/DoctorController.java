package com.bookMyDoctor.controller;

import com.bookMyDoctor.entity.User;
import com.bookMyDoctor.model.bindingModel.DoctorRegistrationModel;
import com.bookMyDoctor.model.bindingModel.EditDoctorModel;
import com.bookMyDoctor.model.bindingModel.EditDoctorPictureModel;
import com.bookMyDoctor.model.viewModel.DoctorViewModel;
import com.bookMyDoctor.service.DoctorService;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;


@Controller
public class DoctorController {
    private DoctorService doctorService;

   

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
       
    }

    @GetMapping("/doctors/{id}")
    public String getDoctor(@PathVariable long id, Model model) {
        DoctorViewModel doctorViewModel = this.doctorService.getViewModelById(id);

        model.addAttribute("doctorViewModel", doctorViewModel);

        return "doctor/doctor";
    }

    @GetMapping({"/", "/doctors"})
    public String getDoctors(Model model, @PageableDefault(size = 8) Pageable pageable) {
        Page<DoctorViewModel> doctors = this.doctorService.getAll(pageable);
        model.addAttribute("doctors", doctors);

        return "doctor/doctors";
    }

    @GetMapping("/register-doctor")
    public String getDoctorRegister(@ModelAttribute DoctorRegistrationModel doctorRegistrationModel, Model model) {
        

        return "doctor/register";
    }

    @PostMapping("/register-doctor")
    public String registerDoctor(@Valid @ModelAttribute DoctorRegistrationModel doctorRegistrationModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "doctor/register";
        }

        this.doctorService.create(doctorRegistrationModel);

        return "redirect:/";
    }

    @GetMapping("/doctor/edit")
    public String getEditDoctor(Model model, Authentication principal) {
        

        long userId = ((User) principal.getPrincipal()).getId();
        EditDoctorModel editDoctorModel = this.doctorService.getEditModelByUserId(userId);

        model.addAttribute("editDoctorModel", editDoctorModel);

        return "doctor/edit";
    }

    @PostMapping("/doctor/edit")
    public String editDoctor(@Valid @ModelAttribute EditDoctorModel editDoctorModel, BindingResult bindingResult,
                             Authentication principal, Model model) {
        if (bindingResult.hasErrors()) {
            return "doctor/edit";
        }

        long userId = ((User) principal.getPrincipal()).getId();
        EditDoctorModel editDoctorModelId = this.doctorService.getEditModelByUserId(userId);
        editDoctorModel.setId(editDoctorModelId.getId());

        this.doctorService.save(editDoctorModel);

        return "redirect:/";
    }

    @GetMapping("/doctor/picture")
    public ResponseEntity<EditDoctorPictureModel> getDoctorPicture(Authentication principal) {
        long userId = ((User) principal.getPrincipal()).getId();
        EditDoctorPictureModel editDoctorPictureModel = this.doctorService.getPictureModelByUserId(userId);
        editDoctorPictureModel.setPicturePath("/mm_pics/" + editDoctorPictureModel.getPicturePath());

        return ResponseEntity.ok(editDoctorPictureModel);
    }

    @PostMapping("/doctor/edit-picture")
    @ResponseBody
    public String addPictures(MultipartHttpServletRequest request, Authentication principal) {
        Iterator<String> itr = request.getFileNames();
        String imageFolderPath = "C:/Users/shahm/Pictures/doctor_pic/";

        MultipartFile picture = request.getFile(itr.next());

        if (picture.isEmpty()) {
            return "Error";
        }

        UUID uniquePicName = UUID.randomUUID();
        String imageFormat = FilenameUtils.getExtension(picture.getOriginalFilename());
        String pictureName = uniquePicName + "." + imageFormat;
        String filePath = imageFolderPath + pictureName;
        File dest = new File(filePath);

        try {
            picture.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long userId = ((User) principal.getPrincipal()).getId();
        long doctorId = this.doctorService.getByUserId(userId).getId();

        EditDoctorPictureModel editDoctorPictureModel = new EditDoctorPictureModel();
        editDoctorPictureModel.setId(doctorId);
        editDoctorPictureModel.setPicturePath(pictureName);

        this.doctorService.savePicture(editDoctorPictureModel);

        return "Success";
    }
}
