package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.SanPham;
import com.example.dreambackend.requests.SanPhamRequest;
import com.example.dreambackend.respones.SanPhamRespone;
import com.example.dreambackend.services.chatlieu.ChatLieuService;
import com.example.dreambackend.services.coao.CoAoService;
import com.example.dreambackend.services.sanpham.SanPhamService;
import com.example.dreambackend.services.thuonghieu.ThuongHieuService;
import com.example.dreambackend.services.xuatxu.XuatXuService;
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
@RequestMapping("/api/san-pham")
// cho phép các request Angular truy cập vào các API
@CrossOrigin(origins = "http://localhost:4200")
public class SanPhamController {
    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    ThuongHieuService thuongHieuService;
    @Autowired
    ChatLieuService chatLieuService;
    @Autowired
    CoAoService coAoService;
    @Autowired
    XuatXuService xuatXuService;

    @GetMapping("/hien-thi")
    public ResponseEntity<Page<SanPhamRespone>> hienThi(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<SanPhamRespone> listSanPham = sanPhamService.getAllSanPham(pageable);
        return ResponseEntity.ok(listSanPham);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addSanPham(@RequestBody SanPhamRequest sanPhamRequest) {
        sanPhamService.addSanPham(sanPhamRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Thêm thành công");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> updateSanPham(@RequestBody SanPhamRequest sanPhamRequest) {
        sanPhamService.updateSanPham(sanPhamRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Sửa thành công");
        return ResponseEntity.ok(response);
    }
}
