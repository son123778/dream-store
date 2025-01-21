package com.example.dreambackend.services.chatlieu;

import com.example.dreambackend.dtos.ChatLieuDto;
import com.example.dreambackend.entities.ChatLieu;
import com.example.dreambackend.requests.ChatLieuRequest;
import com.example.dreambackend.respones.ChatLieuRespone;

import java.util.List;

public interface IChatLieuService {
    List<ChatLieuRespone> getAllChatLieu();

    ChatLieu addChatLieu(ChatLieuRequest chatLieuRequest);

    ChatLieu getChatLieuById(Integer id);

    ChatLieu updateChatLieu(ChatLieuRequest chatLieuRequest);
}
