package com.jachil.onlinewhiteboard.service;

import com.jachil.onlinewhiteboard.model.Room;
import java.util.List;
import java.util.Optional;

public interface RoomService {

    Room save(Room room);

    Room update(Room room);

    void deleteRoom(String roomId);

    Optional<Room> getRoom(String roomId);

    List<Room> getAllRooms();
}
