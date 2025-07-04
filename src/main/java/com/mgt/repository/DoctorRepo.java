package com.mgt.repository;

import com.mgt.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor , Long> {

    Doctor findByUserId(Long userId);


}
