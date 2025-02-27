package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.request.NhanVienRequest;
import com.example.dreambackend.services.nhanvien.INhanVienService;
import com.example.dreambackend.services.nhanvien.NhanVienService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/nhan-vien")
@CrossOrigin(origins = "http://localhost:4200")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Configuration
    public class WebMvcConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/uploads/images/**")
                    .addResourceLocations("file:D:/dream-store/dream-backend/uploads/images/");
        }
    }

    // Hiển thị danh sách nhân viên có phân trang
    @GetMapping("/hien-thi")
    public ResponseEntity<Page<NhanVien>> hienThiPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {
        Page<NhanVien> pagedNhanViens = nhanVienService.getAllNhanVienPaged(page, size);
        return ResponseEntity.ok(pagedNhanViens);
    }

    // API thêm nhân viên
    @PostMapping("/add")
    public ResponseEntity<NhanVien> addNhanVien(@RequestBody NhanVien nhanVien) {
        NhanVien createdNhanVien = nhanVienService.addNhanVien(nhanVien);
        return new ResponseEntity<>(createdNhanVien, HttpStatus.CREATED);
    }

    // API thêm ảnh cho nhân viên
    @PostMapping(value = "/add-image/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<NhanVien> addImageForNhanVien(@PathVariable Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        NhanVien updatedNhanVien = nhanVienService.addImageForNhanVien(id, file);
        return new ResponseEntity<>(updatedNhanVien, HttpStatus.OK);
    }


    // Cập nhật nhân viên
    @PostMapping("/update")
    public ResponseEntity<NhanVien> updateNhanVien(@RequestBody NhanVien nhanVien) {
        NhanVien updatedNhanVien = nhanVienService.updateNhanVien(nhanVien);
        return ResponseEntity.ok(updatedNhanVien);
    }

    // Tìm kiếm nhân viên theo tên
    @GetMapping("/search")
    public List<NhanVien> searchNhanVienByName(@RequestParam("ten") String ten) {
        return nhanVienService.searchNhanVienByName(ten);
    }

    // Lấy chi tiết nhân viên theo id
    @GetMapping("/{id}")
    public ResponseEntity<NhanVien> getNhanVienDetail(@PathVariable Integer id) {
        try {
            NhanVien nhanVien = nhanVienService.getNhanVienById(id);
            return ResponseEntity.ok(nhanVien);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
