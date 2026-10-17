package com.hostelportal.hostelportalbackend.repository;

import com.hostelportal.hostelportalbackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
