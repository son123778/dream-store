package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.services.NhanVien.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping
    public List<NhanVien> getAllNhanVien(@RequestParam(required = false) Integer trangThai,
                                         @RequestParam(required = false) String keyword) {
        return nhanVienService.getFilteredNhanVien(trangThai, keyword);
    }

    @PostMapping
    public NhanVien createNhanVien(@RequestBody NhanVien nhanVien) {
        return nhanVienService.saveNhanVien(nhanVien);
    }

    @PutMapping("/{id}")
    public NhanVien updateNhanVien(@PathVariable int id, @RequestBody NhanVien nhanVien) {
        return nhanVienService.updateNhanVien(id, nhanVien);
    }
}