package com.jachil.onlinewhiteboard.controller;

import com.jachil.onlinewhiteboard.model.*;
import com.jachil.onlinewhiteboard.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class WebSocketTextController {

    @Autowired
    private RoomService roomService;

    @Autowired
    SimpMessagingTemplate template;

    @MessageMapping("/send/{roomId}")
    @SendTo("/topic/{roomId}")
    public Coordinates sendMessage(@Payload Coordinates coordinates) {
        return coordinates;
    }

    @MessageMapping("/send/{roomId}/user")
    @SendTo("/topic/{roomId}/user")
    public ActionResponseDTO joinUser(@Payload RoomActionsDTO roomActions) {
        String username = roomActions.getUsername();
        String action = roomActions.getPayload();
        String roomId = roomActions.getRoomId();

        Optional<Room> roomOptional = roomService.getRoom(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            List<String> participants = room.getParticipants();

            if ("CONNECT_USER".equals(action)) {
                participants.add(username);
                room.setParticipants(participants);
                roomService.update(room);

                String message = "User " + username + " has successfully connected!";
                return new ActionResponseDTO(message, room.getParticipants());
            } else if ("DISCONNECT_USER".equals(action)) {
                participants.remove(username);
                room.setParticipants(participants);

                if (participants.isEmpty()) {
                    roomService.deleteRoom(roomId);
                } else {
                    roomService.update(room);
                }

                String message = "User " + username + " has disconnected!";
                return new ActionResponseDTO(message, room.getParticipants());
            } else {
                return new ActionResponseDTO("Not supported", new ArrayList<String>());
            }
        } else {
            return new ActionResponseDTO("Room not found", new ArrayList<String>());
        }
    }
}

