//package com.example.dreambackend.services.vaitro;
//
//import com.example.dreambackend.entities.VaiTro;
//import com.example.dreambackend.repositories.VaiTroRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class VaiTroService {
//
//    @Autowired
//    private VaiTroRepository vaiTroRepository;
//
//    public List<VaiTro> getAllVaiTros() {
//        return vaiTroRepository.findAll();
//    }
//
//    public VaiTro getVaiTroById(int id) {
//        return vaiTroRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Vai trò không tồn tại"));
//    }
//}
