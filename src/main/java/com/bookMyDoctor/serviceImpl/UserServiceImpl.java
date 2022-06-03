package com.bookMyDoctor.serviceImpl;

import com.bookMyDoctor.entity.Role;
import com.bookMyDoctor.entity.User;
import com.bookMyDoctor.error.Errors;
import com.bookMyDoctor.model.bindingModel.ChangePasswordModel;
import com.bookMyDoctor.model.bindingModel.UserRegistrationModel;
import com.bookMyDoctor.repository.UserRepository;
import com.bookMyDoctor.service.RoleService;
import com.bookMyDoctor.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserRepository userRepository;

    private RoleService roleService;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserRepository userRepository,
                           RoleService roleService,
                           ModelMapper modelMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }

        return user;
    }

    @Override
    public User register(UserRegistrationModel registrationModel) {
        User user = this.modelMapper.map(registrationModel, User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.addRole(this.roleService.getDefaultRole());

        // Adding role for patient or doctor
        if (registrationModel.getAdditionalRole() != null) {
            Role additionalRole = this.roleService.getRoleByAuthority(registrationModel.getAdditionalRole());
            
            user.addRole(additionalRole);
        }

        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public boolean updatePassword(ChangePasswordModel changePasswordModel) {
        User user = this.userRepository.findOne(changePasswordModel.getUserId());
        String encryptedPassword = this.bCryptPasswordEncoder.encode(changePasswordModel.getPassword());
        if (!this.bCryptPasswordEncoder.matches(changePasswordModel.getOldPassword(),
                user.getPassword())) {
            return false;
        }

        user.setPassword(encryptedPassword);

        this.userRepository.saveAndFlush(user);

        return true;
    }
}
