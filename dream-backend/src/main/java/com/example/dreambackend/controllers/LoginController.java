package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.services.nhanvien.NhanVienService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private NhanVienService nhanVienService;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    /**
     * API đăng nhập
     */

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            // Gọi phương thức login từ NhanVienService để kiểm tra thông tin và thực hiện đăng nhập
            ResponseEntity<?> response = nhanVienService.login(email, password);

            // Trả về kết quả login từ service
            return response;
        } catch (Exception e) {
            // Xử lý lỗi nếu có (lỗi hệ thống hoặc lỗi không xác định)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi hệ thống, vui lòng thử lại sau.");
        }
    }
    }

