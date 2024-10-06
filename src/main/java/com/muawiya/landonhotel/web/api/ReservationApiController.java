package com.muawiya.landonhotel.web.api;

import com.muawiya.landonhotel.data.entities.Reservation;
import com.muawiya.landonhotel.data.repository.ReservationRepository;
import com.muawiya.landonhotel.web.exception.BadRequestException;
import com.muawiya.landonhotel.web.exception.NotFoundException;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {

    private final ReservationRepository reservationRepository;

    public ReservationApiController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public List<Reservation> getAllReservationByDate(@PathVariable(value = "date", required = false) String dateString) {
        if (StringUtils.isNotBlank(dateString)) {
            Date date = new Date(new java.util.Date().getTime());
            return reservationRepository.getAllByReservationDate(date);
        }
        return this.reservationRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return this.reservationRepository.save(reservation);
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable long id) {
        return reservationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Reservation not found with id " + id)
        );
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable long id, @RequestBody Reservation reservation) {
        Optional<Reservation> repositoryById = reservationRepository.findById(id);
        if (repositoryById == null) {
            throw new BadRequestException("Reservation not found with id " + id);
        }
        return reservationRepository.save(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable long id) {
        reservationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Reservation not found with id " + id)
        );
        reservationRepository.deleteById(id);
    }


}
