package com.bookMyDoctor.repository;

import com.bookMyDoctor.entity.DaySchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayScheduleRepository extends JpaRepository<DaySchedule, Long> {
	default DaySchedule findOne(long id) {
		return findById(id).orElse(null);
	}

}
