package com.example.dreambackend.services.chatlieu;

import com.example.dreambackend.entities.ChatLieu;
import com.example.dreambackend.repositories.ChatLieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatLieuService implements IChatLieuService {
    @Autowired
    ChatLieuRepository chatLieuRepository;

    @Override
    public List<ChatLieu> getAllChatLieu() {
        return chatLieuRepository.findAll();
    }
}
