package com.example.dreambackend.controllers;

import com.example.dreambackend.requests.SizeRequest;
import com.example.dreambackend.responses.SizeRespone;
import com.example.dreambackend.services.size.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/size")
// cho phép các request Angular truy cập vào các API
@CrossOrigin(origins = "http://localhost:4200")
public class SizeController {
    @Autowired
    SizeService sizeService;
    @GetMapping("/hien-thi")
    public ResponseEntity<List<SizeRespone>> hienThi() {
        List<SizeRespone> listSize = sizeService.getAllSize();
        return ResponseEntity.ok(listSize);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody SizeRequest sizeRequest) {
        sizeService.addSize(sizeRequest);
        return ResponseEntity.ok("Thêm thành công");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SizeRequest sizeRequest) {
        sizeService.updateSize(sizeRequest);
        return ResponseEntity.ok("Sửa thành công");
    }
}
