package com.substring.chat.service;

import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import com.substring.chat.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepo;

//    public RoomService(RoomRepo roomRepo) {
//        this.roomRepo = roomRepo;
//    }

    public ResponseEntity<?> createRoom(String roomId) {
        if (roomRepo.findByRoomId(roomId) != null) {
            //room is already there
            return ResponseEntity.badRequest().body("Room already exists!");
        }

        //create new room
        Room room = new Room();
        room.setRoomId(roomId);
        Room savedRoom = roomRepo.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    public ResponseEntity<?> joinRoom(String roomId) {
        Room room = roomRepo.findByRoomId(roomId);
        if (room == null) {
            return ResponseEntity.badRequest()
                    .body("Room not found!!");
        }
        return ResponseEntity.ok(room);
    }


    public ResponseEntity<List<Message>> getMessagesInChat(String roomId, int page, int size) {
        Room room = roomRepo.findByRoomId(roomId);

        if (room == null) {
            return ResponseEntity.badRequest().build();
        }
//        get messages  and pagination

        //get messages :
        //pagination
        List<Message> messages = room.getMessages();
        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);
        List<Message> paginatedMessages = messages.subList(start, end);
        return ResponseEntity.ok(paginatedMessages);

    }
}