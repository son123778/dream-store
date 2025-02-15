package com.example.dreambackend.controllers;
import com.example.dreambackend.requests.ThuongHieuRequest;
import com.example.dreambackend.responses.ThuongHieuRespone;
import com.example.dreambackend.services.thuonghieu.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok("Thêm thành công");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ThuongHieuRequest thuongHieuRequest) {
        thuongHieuService.updateThuongHieu(thuongHieuRequest);
        return ResponseEntity.ok("Sửa thành công");
    }
}
