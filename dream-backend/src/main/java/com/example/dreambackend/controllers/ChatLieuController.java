package com.example.dreambackend.controllers;

import com.example.dreambackend.dtos.ChatLieuDto;
import com.example.dreambackend.entities.ChatLieu;
import com.example.dreambackend.requests.ChatLieuRequest;
import com.example.dreambackend.respones.ChatLieuRespone;
import com.example.dreambackend.services.chatlieu.ChatLieuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-lieu")
// cho phép các request Angular truy cập vào các API
@CrossOrigin(origins = "http://localhost:4200")
public class ChatLieuController {
    @Autowired
    ChatLieuService chatLieuService;

    @GetMapping("/hien-thi")
    public ResponseEntity<List<ChatLieuRespone>> hienThi(){
        List<ChatLieuRespone> chatLieuRespones = chatLieuService.getAllChatLieu();
        return ResponseEntity.ok(chatLieuRespones);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ChatLieuRequest chatLieuRequest){
        chatLieuService.addChatLieu(chatLieuRequest);
        return ResponseEntity.ok("Thêm thành công");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ChatLieuRequest chatLieuRequest){
        chatLieuService.updateChatLieu(chatLieuRequest);
        return ResponseEntity.ok("Sửa thành công");
    }
}
