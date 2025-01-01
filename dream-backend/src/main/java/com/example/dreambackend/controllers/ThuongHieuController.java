package com.example.dreambackend.controllers;
import com.example.dreambackend.entities.ThuongHieu;
import com.example.dreambackend.services.thuonghieu.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/thuong-hieu")
public class ThuongHieuController {
    @Autowired
    ThuongHieuService thuongHieuService;

    @GetMapping("/hien-thi")
    public ResponseEntity<List<ThuongHieu>> hienThi() {
        List<ThuongHieu> listThuongHieu = thuongHieuService.getAllThuongHieu();
        return ResponseEntity.ok(listThuongHieu);
    }
}
