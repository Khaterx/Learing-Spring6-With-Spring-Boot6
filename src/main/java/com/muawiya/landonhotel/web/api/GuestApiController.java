package com.muawiya.landonhotel.web.api;

import com.muawiya.landonhotel.data.entities.Guest;
import com.muawiya.landonhotel.data.repository.GuestRepository;
import com.muawiya.landonhotel.web.exception.BadRequestException;
import com.muawiya.landonhotel.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestApiController {
    private final GuestRepository guestRepository;

    public GuestApiController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping
    public List<Guest> getAllGuest() {
        return guestRepository.findAll();
    }

    @GetMapping("/{id}")
    public Guest getGuestById(@PathVariable long id) {
        return guestRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Guest not found with id " + id)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest createGuest(@RequestBody Guest guest) {
        return guestRepository.save(guest);
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable long id, @RequestBody Guest guest) {
        if (id != guest.getGuestId()) {
            throw new BadRequestException("id mismatch for guest " + id);
        }
        return guestRepository.save(guest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuest(@PathVariable long id) {
        guestRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Guest not found with id " + id)
        );
        guestRepository.deleteById(id);
    }

}
