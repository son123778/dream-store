package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.HoaDon;
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

    @PostMapping("/{hoaDonId}/add/{sanPhamChiTietId}")
    public ResponseEntity<?> addSanPhamToHoaDon(
            @PathVariable Integer hoaDonId,
            @PathVariable Integer sanPhamChiTietId,
            @RequestParam Integer soLuong) {
        try {
            HoaDonChiTietResponse response = hoaDonChiTietService.addSanPhamToHoaDon(hoaDonId, sanPhamChiTietId, soLuong);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateHoaDonChiTiet(@PathVariable Integer id, @RequestParam Integer soLuong) {
        try {
            HoaDonChiTietResponse response = hoaDonChiTietService.updateHoaDonChiTiet(id, soLuong);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}/remove")
    public ResponseEntity<?> removeSanPhamFromHoaDon(@PathVariable Integer id) {
        try {
            hoaDonChiTietService.removeSanPhamFromHoaDon(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            HoaDonChiTietResponse response = hoaDonChiTietService.findById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{hoaDonId}/all")
    public ResponseEntity<List<HoaDonChiTietResponse>> findByHoaDon(@PathVariable Integer hoaDonId) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(hoaDonId);
        List<HoaDonChiTietResponse> response = hoaDonChiTietService.findByHoaDon(hoaDon);
        return ResponseEntity.ok(response);
    }
}
