package com.jachil.onlinewhiteboard.controller;


import com.jachil.onlinewhiteboard.model.*;
import com.jachil.onlinewhiteboard.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        roomService.save(room);
        return ResponseEntity.ok(room);
    }

    @PostMapping("/{roomId}/save-drawing")
    public ResponseEntity<String> saveDrawing(@PathVariable String roomId, @RequestBody Drawing drawing) {
        Optional<Room> roomOptional = roomService.getRoom(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            room.getDrawings().add(drawing);
            roomService.update(room);
            return ResponseEntity.ok("Drawing saved successfully!");
        } else {
            return ResponseEntity.badRequest().body("Room not found");
        }
    }

    @GetMapping("/{roomId}/get-drawings")
    public ResponseEntity<List<Drawing>> getDrawings(@PathVariable String roomId) {
        Optional<Room> roomOptional = roomService.getRoom(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            return ResponseEntity.ok(room.getDrawings());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable String id) {
        Optional<Room> room = roomService.getRoom(id);
        return room.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
}