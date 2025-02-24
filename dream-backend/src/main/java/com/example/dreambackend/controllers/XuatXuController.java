package com.example.dreambackend.controllers;
import com.example.dreambackend.dtos.XuatXuDto;
import com.example.dreambackend.entities.XuatXu;
import com.example.dreambackend.requests.XuatXuRequest;
import com.example.dreambackend.respones.XuatXuRespone;
import com.example.dreambackend.services.xuatxu.XuatXuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/xuat-xu")
// cho phép các request Angular truy cập vào các API
@CrossOrigin(origins = "http://localhost:4200")
public class XuatXuController {
    @Autowired
    XuatXuService xuatXuService;

    @GetMapping("/hien-thi")
    public ResponseEntity<List<XuatXuRespone>> hienThi() {
        List<XuatXuRespone> listXuatXu = xuatXuService.getAllXuatXu();
        return ResponseEntity.ok(listXuatXu);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody XuatXuRequest xuatXuRequest) {
        xuatXuService.addXuatXu(xuatXuRequest);
        return ResponseEntity.ok("Thêm thành công");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody XuatXuRequest xuatXuRequest) {
        xuatXuService.updateXuatXu(xuatXuRequest);
        return ResponseEntity.ok("Sửa thành công");
    }
}
