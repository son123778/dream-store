package com.example.dreambackend.controllers;
import com.example.dreambackend.dtos.ThuongHieuDto;
import com.example.dreambackend.entities.ThuongHieu;
import com.example.dreambackend.requests.ThuongHieuRequest;
import com.example.dreambackend.respones.ThuongHieuRespone;
import com.example.dreambackend.services.thuonghieu.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/thuong-hieu")
// cho phép các request Angular truy cập vào các API
@CrossOrigin(origins = "http://localhost:4200")
public class ThuongHieuController {
    @Autowired
    ThuongHieuService thuongHieuService;

    @GetMapping("/hien-thi")
    public ResponseEntity<List<ThuongHieuRespone>> hienThi() {
        List<ThuongHieuRespone> listThuongHieu = thuongHieuService.getAllThuongHieu();
        return ResponseEntity.ok(listThuongHieu);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ThuongHieuRequest thuongHieuRequest) {
        thuongHieuService.addThuongHieu(thuongHieuRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Thêm thành công");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ThuongHieuRequest thuongHieuRequest) {
        thuongHieuService.updateThuongHieu(thuongHieuRequest);
        return ResponseEntity.ok("Sửa thành công");
    }
}
