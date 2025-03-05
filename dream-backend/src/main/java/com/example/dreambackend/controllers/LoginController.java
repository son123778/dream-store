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
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            // Gọi phương thức login từ NhanVienService và nhận ResponseEntity
            ResponseEntity<?> response = nhanVienService.login(email, password);

            // Nếu đăng nhập thành công, trả về thông tin nhân viên hoặc thông báo thành công
            return response; // Đảm bảo rằng phương thức login trả về ResponseEntity từ NhanVienService
        } catch (RuntimeException e) {
            // Nếu có lỗi (đăng nhập thất bại), trả về lỗi 401 Unauthorized với thông báo lỗi
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Đăng nhập thất bại: " + e.getMessage());  // Trả về thông báo lỗi chi tiết
        }
    }
    }

