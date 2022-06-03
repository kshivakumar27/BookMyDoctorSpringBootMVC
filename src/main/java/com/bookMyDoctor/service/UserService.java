package com.bookMyDoctor.service;

import com.bookMyDoctor.entity.User;
import com.bookMyDoctor.model.bindingModel.ChangePasswordModel;
import com.bookMyDoctor.model.bindingModel.UserRegistrationModel;

import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {
    User register(UserRegistrationModel registrationModel);
    

    boolean updatePassword(ChangePasswordModel changePasswordModel);
}
