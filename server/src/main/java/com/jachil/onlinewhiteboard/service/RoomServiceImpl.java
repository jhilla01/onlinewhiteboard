package com.jachil.onlinewhiteboard.service;

import com.jachil.onlinewhiteboard.DAO.RoomDao;
import com.jachil.onlinewhiteboard.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomDao roomDao;
    @Autowired
    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public Room save(Room room) {
        roomDao.add(room);
        return room;
    }

    @Override
    public Room update(Room room) {
        roomDao.update(room);
        return room;
    }

    @Override
    public void deleteRoom(String roomId) {
        Room room = getRoom(roomId).orElseThrow(() -> new IllegalArgumentException("No room found with id: " + roomId));
        roomDao.remove(room);
    }

    @Override
    public Optional<Room> getRoom(String roomId) {
        Room room = roomDao.get(roomId);
        return Optional.ofNullable(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDao.getAll();
    }
}