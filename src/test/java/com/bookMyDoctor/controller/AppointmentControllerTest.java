package com.bookMyDoctor.controller;


import com.bookMyDoctor.entity.User;
import com.bookMyDoctor.model.viewModel.AppointmentViewModel;
import com.bookMyDoctor.model.viewModel.DoctorSelectViewModel;
import com.bookMyDoctor.service.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AppointmentController.class)
@ActiveProfiles("test")
@EnableSpringDataWebSupport
public class AppointmentControllerTest {
    private static final long USER_ID = 0;
    private static final long APPOINTMENT_ID = 1;
    private static final String DESCRIPTION = "Pain in the back";
    private static final String DATE_MM_DD_YYYY = "12/02/1998";

    private static Date DATE;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppointmentService appointmentService;


    @MockBean
    private PatientService patientService;

    @MockBean
    private DoctorService doctorService;



    @MockBean
    Authentication principal;

    @Before
    public void setUp() throws Exception {
        //Arrange
        AppointmentViewModel appointmentViewModel = new AppointmentViewModel();
        appointmentViewModel.setId(APPOINTMENT_ID);

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        DATE = formatter.parse(DATE_MM_DD_YYYY);

        DoctorSelectViewModel doctorSelectViewModel = new DoctorSelectViewModel();
        doctorSelectViewModel.setId(0);

        appointmentViewModel.setDate(DATE);
        appointmentViewModel.setDescription(DESCRIPTION);
        appointmentViewModel.setDoctorSelectViewModel(doctorSelectViewModel);
        when(this.appointmentService.getById(APPOINTMENT_ID)).thenReturn(appointmentViewModel);


        User user = new User();
        user.setId(USER_ID);

        when(this.principal.getPrincipal()).thenReturn(user);
    }

    @Test
    public void showAppointmentGivenValidModel_ShouldReturnOkStatus() throws Exception {
        //Act
        this.mvc
                .perform(get("/appointment/getForDate?date=" + DATE_MM_DD_YYYY))
                .andExpect(status().isOk());
    }

    @Test
    public void showAppointmentGivenValidModel_ShouldCallServiceOnce() throws Exception {
        //Act
        this.mvc
                .perform(get("/appointment/getForDate?date=" + DATE_MM_DD_YYYY));
        verify(this.appointmentService, times(1)).getAllForDateAndDoctor(DATE, 0);
        verifyNoMoreInteractions(this.appointmentService);
    }

}