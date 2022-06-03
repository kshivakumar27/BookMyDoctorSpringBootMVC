package com.bookMyDoctor.model.bindingModel;

import com.bookMyDoctor.customValidation.BGTelephone;
import com.bookMyDoctor.customValidation.IsPasswordsMatching;
import com.bookMyDoctor.customValidation.PasswordConfirmable;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@IsPasswordsMatching
public class PatientRegistrationModel implements PasswordConfirmable {

    @NotBlank(message = "Invalid email address")
    @Email(message = "Invalid email address")
    private String email;

    @Size(min = 2, message = "Username too short")
    private String username;

    @Size(min = 5, message = "Password too short")
    private String password;

    @NotBlank
    private String confirmPassword;

    @Size(min = 2, message = "First name too short")
    private String firstName;

    @Size(min = 2, message = "Last name too short")
    private String lastName;

    

    @BGTelephone
    private String telephoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Invalid date of birth")
    private Date dateOfBirth;

    private Date dateOfEnrollment;



    private long doctor;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(Date dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public long getDoctor() {
        return doctor;
    }

    public void setDoctor(long doctor) {
        this.doctor = doctor;
    }
}
