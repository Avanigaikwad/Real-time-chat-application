package com.substring.chat.controller;

import com.substring.chat.config.AppConstants;
import com.substring.chat.entities.Message;
import com.substring.chat.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rooms")
@CrossOrigin(AppConstants.FRONT_END_BASE_URL)
public class RoomController {

    @Autowired
    private RoomService roomService;

//        create room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId) {
        return roomService.createRoom(roomId);
    }

//    get room
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
        System.out.println("Received roomId: " + roomId); // Debugging
        return roomService.joinRoom(roomId);
    }


    //    get messages of room
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<?> getMessages(@PathVariable String roomId,
                                         @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                         @RequestParam(value = "size", defaultValue = "20", required = false) int size) {
        return  roomService.getMessagesInChat(roomId,page,size);
    }
}