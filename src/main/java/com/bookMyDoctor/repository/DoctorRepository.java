package com.bookMyDoctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookMyDoctor.entity.Doctor;



@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Doctor findOneByUserId(long userId);
	
	List<Doctor> findAllByOrderByFirstNameAscLastName();

	default Doctor findOne(long id) {
		return findById(id).orElse(null);
	}

}
