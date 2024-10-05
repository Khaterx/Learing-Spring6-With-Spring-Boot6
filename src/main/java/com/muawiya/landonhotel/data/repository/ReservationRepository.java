package com.muawiya.landonhotel.data.repository;

import com.muawiya.landonhotel.data.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> getAllByReservationDate(Date date);
}
