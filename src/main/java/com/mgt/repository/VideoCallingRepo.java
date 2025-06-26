package com.mgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgt.model.videoCalling;



public interface VideoCallingRepo extends JpaRepository<videoCalling,Long> {

    videoCalling findByVideoLink(String videoLink);

}
