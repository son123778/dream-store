package com.example.dreambackend.controllers;

import com.example.dreambackend.entities.CoAo;
import com.example.dreambackend.services.coao.CoAoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/co-ao")
public class CoAoController {
    @Autowired
    CoAoService coAoService;

    @GetMapping("/hien-thi")
    public ResponseEntity<List<CoAo>> hienThi() {
        List<CoAo> listCoAo = coAoService.getAllCoAo();
        return ResponseEntity.ok(listCoAo);
    }
}
