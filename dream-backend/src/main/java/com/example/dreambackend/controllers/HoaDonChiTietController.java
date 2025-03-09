package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.HoaDon;
import com.example.dreambackend.requests.HoaDonChiTietSearchRequest;
import com.example.dreambackend.requests.HoaDonChiTietRequest;
import com.example.dreambackend.requests.HoaDonChiTietSearchRequest;
import com.example.dreambackend.responses.HoaDonChiTietResponse;
import com.example.dreambackend.services.hoadonchitiet.IHoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoa-don-chi-tiet")
public class HoaDonChiTietController {

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

    @PostMapping("/create")
    public ResponseEntity<?> addSanPhamToHoaDon(
            @RequestBody HoaDonChiTietRequest request
    ) {
        try {
            HoaDonChiTietResponse response = hoaDonChiTietService.addSanPhamToHoaDon(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateHoaDonChiTiet(
            @PathVariable Integer id,
            @RequestParam Integer soLuong
    ) {
        try {
            HoaDonChiTietResponse response = hoaDonChiTietService.updateHoaDonChiTiet(id, soLuong);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/all")
    public ResponseEntity<List<HoaDonChiTietResponse>> findByHoaDon(
            @RequestBody HoaDonChiTietSearchRequest searchRequest
    ) {
        List<HoaDonChiTietResponse> response = hoaDonChiTietService.search(searchRequest);
        return ResponseEntity.ok(response);
    }
}
