package com.bookMyDoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookMyDoctor.entity.WeekSchedule;

@Repository
public interface WeekScheduleRepository extends JpaRepository<WeekSchedule,Long> {

	default WeekSchedule findOne(long id) {
		return findById(id).orElse(null);
	}

}
