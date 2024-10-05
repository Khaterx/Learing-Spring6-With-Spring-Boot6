package com.muawiya.landonhotel.data.repository;

import com.muawiya.landonhotel.data.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomNumberIgnoreCase(String roomNumber);
}
