package com.example.dreambackend.controllers;

import com.example.dreambackend.requests.MauSacRequest;
import com.example.dreambackend.respones.MauSacRepone;
import com.example.dreambackend.services.mausac.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mau-sac")
// cho phép các request Angular truy cập vào các API
@CrossOrigin(origins = "http://localhost:4200")
public class MauSacController {
    @Autowired
    MauSacService mauSacService;
    @GetMapping("hien-thi")
    public ResponseEntity<List<MauSacRepone>> hienthi() {
        List<MauSacRepone> listMauSac = mauSacService.getAllMauSac();
        return ResponseEntity.ok(listMauSac);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody MauSacRequest mauSacRequest) {
        mauSacService.addMauSac(mauSacRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Thêm thành công");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody MauSacRequest mauSacRequest) {
        mauSacService.updateMauSac(mauSacRequest);
        return ResponseEntity.ok("Sửa thành công");
    }
}
