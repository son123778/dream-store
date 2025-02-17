package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.Anh;
import com.example.dreambackend.respones.AnhRespone;
import com.example.dreambackend.services.anh.AnhService;
import com.example.dreambackend.services.sanpham.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/api/anh")
@CrossOrigin(origins = "http://localhost:4200")
public class AnhController {
    @Autowired
    AnhService anhService;

    @Autowired
    SanPhamService sanPhamService;

    @GetMapping("/hien-thi")
    public ResponseEntity<List<AnhRespone>> hienThi() {
        List<AnhRespone> listAnh = anhService.getAllAnh();
        return ResponseEntity.ok(listAnh);
    }
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addAnhs(
            @RequestParam("anhUrl") List<MultipartFile> anhUrls,
            @RequestParam("trangThai") Integer trangThai,
            @RequestParam("idSanPham") Integer idSanPham) {
        List<Anh> addedAnhs = anhService.addAnhs(anhUrls, trangThai, idSanPham);
        return ResponseEntity.ok("Đã tải lên " + addedAnhs.size() + " ảnh thành công");
    }

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAnhs(
            @RequestParam("anhUrls") List<MultipartFile> anhUrls,
            @RequestParam("trangThais") List<Integer> trangThais,
            @RequestParam("ids") List<Integer> ids,
            @RequestParam("idSanPham") Integer idSanPham) {
        List<Anh> updatedAnhs = anhService.updateAnhs(anhUrls, trangThais, ids, idSanPham);
        return ResponseEntity.ok("Đã sửa thành công " + updatedAnhs.size() + " ảnh");
    }

}
