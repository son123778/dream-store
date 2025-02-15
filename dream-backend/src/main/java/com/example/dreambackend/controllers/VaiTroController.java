package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.VaiTro;
import com.example.dreambackend.requests.VaiTroRequest;
import com.example.dreambackend.responses.VaiTroResponse;
import com.example.dreambackend.services.vaitro.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vai-tro")
@CrossOrigin(origins = "http://localhost:4200") // Đảm bảo kết nối với Angular
public class VaiTroController {

    @Autowired
    private VaiTroService vaiTroService;

    /**
     * Lấy danh sách vai trò
     */
    @GetMapping("/hien-thi")
    public List<VaiTroResponse> getAllVaiTro() {
        return vaiTroService.getAllVaiTro();
    }

    /**
     * Thêm mới vai trò
     */
    @PostMapping("/add")
    public VaiTro addVaiTro(@RequestBody VaiTroRequest vaiTroRequest) {
        return vaiTroService.addVaiTro(vaiTroRequest);
    }

    /**
     * Cập nhật vai trò
     */
    @PutMapping("/update")
    public VaiTro updateVaiTro(@RequestBody VaiTroRequest vaiTroRequest) {
        return vaiTroService.updateVaiTro(vaiTroRequest);
    }
}
