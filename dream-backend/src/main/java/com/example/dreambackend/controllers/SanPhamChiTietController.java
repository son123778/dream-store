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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Page<SanPhamChiTietRespone>> hienThi(
            @RequestParam(value = "idSanPham") Integer idSanPham,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<SanPhamChiTietRespone> listSanPhamChiTiet = sanPhamChiTietService.getSanPhamChiTietBySanPhamId(idSanPham, pageable);

        return ResponseEntity.ok(listSanPhamChiTiet);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody SanPhamChiTietRequest sanPhamChiTietRequest) {
        sanPhamChiTietService.addSanPhamChiTiet(sanPhamChiTietRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Thêm thành công");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SanPhamChiTietRequest sanPhamChiTietRequest) {
        sanPhamChiTietService.updateSanPhamChiTiet(sanPhamChiTietRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Thêm thành công");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tim-kiem")
    public ResponseEntity<Page<SanPhamChiTietRespone>> timKiemSanPhamChiTiet(
            @RequestParam(value = "gia", required = false) Double gia,
            @RequestParam(value = "soLuong", required = false) Integer soLuong,
            @RequestParam(value = "idMauSac", required = false) Integer idMauSac,
            @RequestParam(value = "idSize", required = false) Integer idSize,
            @RequestParam(value = "trangThai", required = false) Integer trangThai,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<SanPhamChiTietRespone> result = sanPhamChiTietService.timKiemSanPhamChiTiet(
                gia, soLuong, idMauSac, idSize, trangThai, pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/xuat-excel", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<byte[]> exportSanPhamChiTietToExcel(@RequestParam("idSanPham") Integer idSanPham) {
        // Lấy danh sách sản phẩm chi tiết từ service
        List<SanPhamChiTietRespone> sanPhamChiTiets = sanPhamChiTietService
                .getSanPhamChiTietBySanPhamId(idSanPham, Pageable.unpaged()).getContent();
        // Gọi service để xuất file Excel
        return sanPhamChiTietService.exportSanPhamChiTietToExcel(sanPhamChiTiets);
    }

}
