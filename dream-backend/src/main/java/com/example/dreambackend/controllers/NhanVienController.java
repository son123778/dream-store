package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.requests.NhanVienRequest;
import com.example.dreambackend.responses.NhanVienResponse;
import com.example.dreambackend.services.nhanvien.NhanVienService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nhan-vien")

@CrossOrigin(origins = "http://localhost:4200") // Cho phép kết nối từ Angular
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    /**
     * Lấy danh sách tất cả nhân viên
     */
    @GetMapping("/hien-thi")
    public List<NhanVienResponse> getAllNhanVien() {
        return nhanVienService.getAllNhanVien();
    }

    /**
     * Tìm kiếm nhân viên theo tên
     */
    @GetMapping("/tim-kiem")
    public List<NhanVienResponse> searchNhanVienByName(@RequestParam String ten) {
        return nhanVienService.searchNhanVienByName(ten);
    }

    /**
     * Lọc nhân viên theo trạng thái (1: Đang làm, 0: Nghỉ việc)
     */
    @GetMapping("/loc-trang-thai")
    public List<NhanVienResponse> filterNhanVienByTrangThai(@RequestParam Integer trangThai) {
        return nhanVienService.filterNhanVienByTrangThai(trangThai);
    }

    /**
     * Thêm nhân viên mới
     */

    @PostMapping("/add")
    public NhanVien addNhanVien(@RequestBody NhanVienRequest nhanVienRequest) {
        return nhanVienService.addNhanVien(nhanVienRequest);
    }

    /**
     * Cập nhật nhân viên
     */

    @PutMapping("/update")
    public NhanVien updateNhanVien(@RequestBody NhanVienRequest nhanVienRequest) {
        return nhanVienService.updateNhanVien(nhanVienRequest);
    }

}
