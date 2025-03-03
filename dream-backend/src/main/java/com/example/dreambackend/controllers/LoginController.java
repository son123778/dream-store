package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.services.nhanvien.NhanVienService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private NhanVienService nhanVienService;


    /**
     * API đăng nhập
     */

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam String email, @RequestParam String password) {
        try {
            // Gọi phương thức login từ NhanVienService
            NhanVien nhanVien = nhanVienService.login(email, password);

            // Nếu đăng nhập thành công, trả về thông tin nhân viên
            return ResponseEntity.ok(nhanVien);
        } catch (RuntimeException e) {
            // Nếu có lỗi (đăng nhập thất bại), trả về lỗi 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());  // Trả về thông báo lỗi (ví dụ: sai mật khẩu hoặc không tìm thấy nhân viên)
        }
    }
    }

