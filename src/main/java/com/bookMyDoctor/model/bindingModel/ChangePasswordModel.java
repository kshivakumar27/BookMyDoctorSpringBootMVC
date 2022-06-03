package com.bookMyDoctor.model.bindingModel;

import com.bookMyDoctor.customValidation.IsPasswordsMatching;
import com.bookMyDoctor.customValidation.PasswordConfirmable;

import javax.validation.constraints.Size;
@IsPasswordsMatching
public class ChangePasswordModel implements PasswordConfirmable {
    private long userId;

    @Size(min = 5, message = "Old password too short")
    private String oldPassword;

    @Size(min = 5, message = "Password too short")
    private String password;

    @Size(min = 5, message = "Confirm password too short")
    private String confirmPassword;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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
}
