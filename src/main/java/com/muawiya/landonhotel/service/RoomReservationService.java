package com.muawiya.landonhotel.service;

import com.muawiya.landonhotel.data.entities.Guest;
import com.muawiya.landonhotel.data.entities.Reservation;
import com.muawiya.landonhotel.data.entities.Room;
import com.muawiya.landonhotel.data.repository.GuestRepository;
import com.muawiya.landonhotel.data.repository.ReservationRepository;
import com.muawiya.landonhotel.data.repository.RoomRepository;
import com.muawiya.landonhotel.service.model.RoomReservation;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoomReservationService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;

    public RoomReservationService(RoomRepository roomRepository, ReservationRepository reservationRepository, GuestRepository guestRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(String reservationDate) {
        Date date = null;
        if (StringUtils.isNotEmpty(reservationDate)) {
            date = Date.valueOf(reservationDate);
        } else {
            date = new Date(new java.util.Date().getTime());
        }

        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        List<Room> rooms = roomRepository.findAll();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        });

        List<Reservation> reservations = reservationRepository.getAllByReservationDate(date);
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setReservationId(reservation.getReservationId());
            roomReservation.setReservationDate(reservation.getReservationDate().toString());
            Optional<Guest> guest = guestRepository.findById(reservation.getGuestId());
            roomReservation.setGuestId(guest.get().getGuestId());
            roomReservation.setFirstName(guest.get().getFirstName());
            roomReservation.setLastName(guest.get().getLastName());

        });

        return roomReservationMap.values().stream().toList();
    }
}
