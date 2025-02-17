package com.example.dreambackend.controllers;

import com.example.dreambackend.requests.SizeRequest;
import com.example.dreambackend.respones.SizeRespone;
import com.example.dreambackend.services.size.SizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> add(@Valid @RequestBody SizeRequest sizeRequest, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        sizeService.addSize(sizeRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Thêm thành công");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SizeRequest sizeRequest) {
        sizeService.updateSize(sizeRequest);
        return ResponseEntity.ok("Sửa thành công");
    }
}
