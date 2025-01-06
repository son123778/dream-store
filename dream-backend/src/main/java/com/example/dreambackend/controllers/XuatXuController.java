package com.example.dreambackend.controllers;
import com.example.dreambackend.entities.XuatXu;
import com.example.dreambackend.services.xuatxu.XuatXuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/xuat-xu")
@CrossOrigin(origins = "http://localhost:4200")
public class XuatXuController {
    @Autowired
    XuatXuService xuatXuService;

    @GetMapping("/hien-thi")
    public ResponseEntity<List<XuatXu>> hienThi() {
        List<XuatXu> listXuatXu = xuatXuService.getAllXuatXu();
        return ResponseEntity.ok(listXuatXu);
    }
}
