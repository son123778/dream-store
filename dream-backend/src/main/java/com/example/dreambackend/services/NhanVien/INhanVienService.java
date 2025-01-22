package com.example.dreambackend.services.NhanVien;

import com.example.dreambackend.entities.NhanVien;

import java.util.List;

public interface INhanVienService {

    List<NhanVien> getFilteredNhanVien(Integer trangThai, String keyword);

    NhanVien saveNhanVien(NhanVien nhanVien);

    NhanVien updateNhanVien(int id, NhanVien updatedNhanVien);
}