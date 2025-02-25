package com.example.dreambackend.services.chatlieu;

import com.example.dreambackend.entities.ChatLieu;
import com.example.dreambackend.repositories.ChatLieuRepository;
import com.example.dreambackend.requests.ChatLieuRequest;
import com.example.dreambackend.responses.ChatLieuRespone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ChatLieuService implements IChatLieuService {
    @Autowired
    ChatLieuRepository chatLieuRepository;

    @Override
    public List<ChatLieuRespone> getAllChatLieu() {
        return chatLieuRepository.getAllChatLieuRepone();
    }

    @Override
    public ChatLieu addChatLieu(ChatLieuRequest chatLieuRequest) {
        ChatLieu chatLieu = new ChatLieu();
        BeanUtils.copyProperties(chatLieuRequest, chatLieu);
        chatLieu.setNgayTao(LocalDate.now());
        chatLieu.setNgaySua(LocalDate.now());
        return chatLieuRepository.save(chatLieu);
    }

    @Override
    public ChatLieu getChatLieuById(Integer idChatLieu) {
        return chatLieuRepository.findById(idChatLieu).orElseThrow(()
                -> new RuntimeException("Không tim được id chất liệu"));
    }

    @Override
    public ChatLieu updateChatLieu(ChatLieuRequest chatLieuRequest) {
        // Lấy đối tượng cần cập nhật từ database
        ChatLieu chatLieuUpdate = chatLieuRepository.findById(chatLieuRequest.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chất liệu với id: " + chatLieuRequest.getId()));
        // Sao chép các thuộc tính từ request sang đối tượng cần cập nhật
        BeanUtils.copyProperties(chatLieuRequest, chatLieuUpdate, "id", "ngayTao");
        chatLieuUpdate.setNgaySua(LocalDate.now());
        return chatLieuRepository.save(chatLieuUpdate);
    }

}
