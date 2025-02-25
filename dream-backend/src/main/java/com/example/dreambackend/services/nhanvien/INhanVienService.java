package com.example.dreambackend.services.nhanvien;

import com.example.dreambackend.entities.NhanVien;
import org.springframework.data.domain.Page;


import java.util.List;

public interface INhanVienService {

    Page<NhanVien> getAllNhanVienPaged(int page, int size);

    NhanVien addNhanVien(NhanVien nhanVien);

    NhanVien updateNhanVien(NhanVien nhanVien);

    NhanVien getNhanVienById(Integer id);

    List<NhanVien> searchNhanVienByName(String ten);
}
