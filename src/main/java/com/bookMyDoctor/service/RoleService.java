package com.bookMyDoctor.service;

import com.bookMyDoctor.entity.Role;


public interface RoleService {
    Role getDefaultRole();

    Role getRoleByAuthority(String authority);
}
