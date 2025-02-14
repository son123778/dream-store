package com.example.dreambackend.controllers;

import com.example.dreambackend.dtos.CoAoDto;
import com.example.dreambackend.entities.CoAo;
import com.example.dreambackend.requests.CoAoRequest;
import com.example.dreambackend.respones.CoAoRespone;
import com.example.dreambackend.services.coao.CoAoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/co-ao")
// cho phép các request Angular truy cập vào các API
@CrossOrigin(origins = "http://localhost:4200")
public class CoAoController {
    @Autowired
    CoAoService coAoService;

    @GetMapping("/hien-thi")
    public ResponseEntity<List<CoAoRespone>> hienThi() {
        List<CoAoRespone> listCoAo = coAoService.getAllCoAo();
        return ResponseEntity.ok(listCoAo);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CoAoRequest coAoRequest) {
        coAoService.addCoAo(coAoRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Thêm thành công");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CoAoRequest coAoRequest) {
        coAoService.updateCoAo(coAoRequest);
        return ResponseEntity.ok("Sửa thành công");
    }
}
