package com.bookMyDoctor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookMyDoctor.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findOneByUsername(String username);

	default User findOne(long userId) {
		return findById(userId).orElse(null);
	}

}
