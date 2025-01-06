package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.ChatLieu;
import com.example.dreambackend.services.chatlieu.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat-lieu")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatLieuController {
    @Autowired
    ChatLieuService chatLieuService;

    @GetMapping("/hien-thi")
    public ResponseEntity<List<ChatLieu>> hienThi(){
        List<ChatLieu> listChatLieu = chatLieuService.getAllChatLieu();
        return ResponseEntity.ok(listChatLieu);
    }
}
