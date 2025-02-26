package com.example.dreambackend.controllers;

import com.example.dreambackend.dtos.SanPhamDto;
import com.example.dreambackend.services.sanphamonline.SanPhamOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RestController
@RequestMapping("/api/ban-hang-online")
@CrossOrigin(origins = "http://localhost:4201")
public class BanHangOnlineController {
    @Autowired
    SanPhamOnlineService sanPhamOnlineService;
    @GetMapping("/hien-thi")
    public ResponseEntity<List<SanPhamDto>> hienThi() {
        List<SanPhamDto> sanPhamDtos = sanPhamOnlineService.getSanPhamOnline();
        return ResponseEntity.ok(sanPhamDtos);
    }
}
