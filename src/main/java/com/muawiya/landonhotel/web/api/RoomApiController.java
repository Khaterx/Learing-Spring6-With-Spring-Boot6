package com.muawiya.landonhotel.web.api;

import com.muawiya.landonhotel.data.entities.Room;
import com.muawiya.landonhotel.data.repository.RoomRepository;
import com.muawiya.landonhotel.web.exception.BadRequestException;
import com.muawiya.landonhotel.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomApiController {

    private final RoomRepository roomRepository;

    public RoomApiController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isEmpty()) {
            throw new NotFoundException("Room with id " + id + "is not found");
        }
        return room.get();
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable long id, @RequestBody Room room) {
        if (id != room.getRoomId()) {
            throw new BadRequestException("id on path doesn't match body");
        }
        return roomRepository.save(room);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isEmpty()) {
            throw new NotFoundException("Room with id " + id + " is not found");
        }
        roomRepository.deleteById(id);
    }
}
