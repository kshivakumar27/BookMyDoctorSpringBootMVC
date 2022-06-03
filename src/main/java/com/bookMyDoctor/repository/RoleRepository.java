package com.bookMyDoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookMyDoctor.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	Role findOneByAuthority(String authority);

}
