package com.mgt.repository;

import com.mgt.model.BookAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookApptRepo extends JpaRepository<BookAppointment , Long> {
}
