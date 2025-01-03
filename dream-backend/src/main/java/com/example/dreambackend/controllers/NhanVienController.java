package com.example.dreambackend.controllers;



import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.repositories.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    // Lấy danh sách nhân viên
    @GetMapping
    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.findAll();
    }

    // Lấy nhân viên theo ID
    @GetMapping("/{id}")
    public NhanVien getNhanVienById(@PathVariable int id) {
        return nhanVienRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Không tìm thấy nhân viên có ID: " + id));
    }
//
//    // Thêm nhân viên mới
//    @PostMapping
//    public NhanVien addNhanVien(@RequestBody NhanVien nhanVien) {
//        return nhanVienRepository.save(nhanVien);
//    }
//
//    // Cập nhật thông tin nhân viên
//    @PutMapping("/{id}")
//    public NhanVien updateNhanVien(@PathVariable int id, @RequestBody NhanVien nhanVien) {
//        NhanVien existing = nhanVienRepository.findById(id).orElseThrow(() ->
//                new RuntimeException("Không tìm thấy nhân viên có ID: " + id));
//
//        existing.setTen(nhanVien.getTen());
//        existing.setNgaySinh(nhanVien.getNgaySinh());
//        existing.setGioiTinh(nhanVien.getGioiTinh());
//        existing.setEmail(nhanVien.getEmail());
//        existing.setSoDienThoai(nhanVien.getSoDienThoai());
//        existing.setTaiKhoan(nhanVien.getTaiKhoan());
//        existing.setMatKhau(nhanVien.getMatKhau());
//
//        return nhanVienRepository.save(existing);
//    }
//
//    // Xóa nhân viên
//    @DeleteMapping("/{id}")
//    public String deleteNhanVien(@PathVariable int id) {
//        nhanVienRepository.deleteById(id);
//        return "Đã xóa nhân viên có ID: " + id;
//    }
}