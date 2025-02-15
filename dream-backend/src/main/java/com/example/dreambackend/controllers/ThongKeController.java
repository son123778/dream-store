package com.example.dreambackend.controllers;

import com.example.dreambackend.responses.ThongKeResponse;
import com.example.dreambackend.responses.ThongKeThangResponse;
import com.example.dreambackend.services.thongke.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thong-ke")
@CrossOrigin(origins = "*")
public class ThongKeController {

    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/{type}")
    public ResponseEntity<ThongKeResponse> thongKeTongQuan(@PathVariable String type) {
        ThongKeResponse response = thongKeService.thongKeTongQuan(type);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/nam-nay/thang")
    public ResponseEntity<List<ThongKeThangResponse>> thongKeTungThang() {
        List<ThongKeThangResponse> response = thongKeService.thongKeTungThang();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/tat-ca/nam")
    public ResponseEntity<List<ThongKeThangResponse>> thongKeTungNam() {
        List<ThongKeThangResponse> response = thongKeService.thongKeTungNam();
        return ResponseEntity.ok(response);
    }

}
