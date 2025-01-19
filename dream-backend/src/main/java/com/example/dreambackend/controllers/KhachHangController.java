package com.example.dreambackend.controllers;

import com.example.dreambackend.dtos.KhachHangDto;
import com.example.dreambackend.entities.KhachHang;
import com.example.dreambackend.services.khachhang.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khach-hang")
@CrossOrigin(origins = "http://localhost:4200")
public class KhachHangController {
    @Autowired
    KhachHangService khachHangService;


    @GetMapping("/hien-thi")
    public ResponseEntity<List<KhachHang>> hienthi(){
        return ResponseEntity.ok(khachHangService.getAllKhachHang());
    }
    @PostMapping
    public ResponseEntity<KhachHang> addKhachHang(@RequestBody KhachHangDto khachHangDto){
        return ResponseEntity.ok(khachHangService.newKhachHang(khachHangDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<KhachHang> updateKhachHang(@PathVariable int id, @RequestBody KhachHangDto khachHangDto){
        return ResponseEntity.ok(khachHangService.updateKhachHang(id, khachHangDto));
    }

}
