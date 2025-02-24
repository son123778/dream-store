package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.SanPhamChiTiet;
import com.example.dreambackend.requests.SanPhamChiTietRequest;
import com.example.dreambackend.respones.SanPhamChiTietRespone;
import com.example.dreambackend.respones.SanPhamRespone;
import com.example.dreambackend.services.mausac.MauSacService;
import com.example.dreambackend.services.sanpham.SanPhamService;
import com.example.dreambackend.services.sanphamchitiet.SanPhamChiTietService;
import com.example.dreambackend.services.size.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/san-pham-chi-tiet")
@CrossOrigin(origins = "http://localhost:4200")
public class SanPhamChiTietController {
    @Autowired
    SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    MauSacService  mauSacService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    SizeService sizeService;

    @GetMapping("/hien-thi")
    public ResponseEntity<Page<SanPhamChiTietRespone>> hienThi(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<SanPhamChiTietRespone> listSanPhamChiTiet = sanPhamChiTietService.getAllSanPhamChiTiet(pageable);
        return ResponseEntity.ok(listSanPhamChiTiet);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody SanPhamChiTietRequest sanPhamChiTietRequest) {
        sanPhamChiTietService.addSanPhamChiTiet(sanPhamChiTietRequest);
        return ResponseEntity.ok("Thêm thành công");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SanPhamChiTietRequest sanPhamChiTietRequest) {
        sanPhamChiTietService.addSanPhamChiTiet(sanPhamChiTietRequest);
        return ResponseEntity.ok("Sửa thành công");
    }
}
