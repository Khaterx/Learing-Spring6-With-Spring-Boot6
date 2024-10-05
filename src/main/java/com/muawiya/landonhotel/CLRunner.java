package com.muawiya.landonhotel;

import com.muawiya.landonhotel.data.repository.GuestRepository;
import com.muawiya.landonhotel.data.repository.ReservationRepository;
import com.muawiya.landonhotel.data.repository.RoomRepository;
import com.muawiya.landonhotel.service.RoomReservationService;
import com.muawiya.landonhotel.service.model.RoomReservation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CLRunner implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    private final RoomReservationService roomReservationService;

    public CLRunner(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository, RoomReservationService roomReservationService) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.roomReservationService = roomReservationService;
    }

    @Override
    public void run(String... args) throws Exception {
//        List<Room> rooms = roomRepository.findAll();
//        Optional<Room> room = roomRepository.findByRoomNumberIgnoreCase("p1");
//        System.out.println(room);
//        rooms.forEach(System.out::println);
//--------------------------------------------------
//        System.out.println("** GUESTS **");
//        List<Guest> guests = guestRepository.findAll();
//        guests.forEach(System.out::println);
//        System.out.println("** ROOMS **");
//        List<Room> rooms = roomRepository.findAll();
//        rooms.forEach(System.out::println);
//        System.out.println("** RESERVATIONS **");
//        List<Reservation> reservations = reservationRepository.findAll();
//        reservations.forEach(System.out::println);
//--------------------------------------------------
        List<RoomReservation> reservations = roomReservationService.getRoomReservationsForDate("2023-08-28");
        reservations.forEach(System.out::println);

    }
}
