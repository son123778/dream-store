package com.example.dreambackend.services.NhanVien;

import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.repositories.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService implements INhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhanVien> getFilteredNhanVien(Integer trangThai, String keyword) {
        if (trangThai == null && (keyword == null || keyword.isEmpty())) {
            return nhanVienRepository.findAll();
        } else if (trangThai != null && (keyword == null || keyword.isEmpty())) {
            return nhanVienRepository.findByTrangThai(trangThai);
        } else if (trangThai == null) {
            return nhanVienRepository.findByKeyword(keyword);
        } else {
            return nhanVienRepository.findByTrangThaiAndKeyword(trangThai, keyword);
        }
    }

    public NhanVien saveNhanVien(NhanVien nhanVien) {
        nhanVien.setNgayTao(java.time.LocalDate.now());
        return nhanVienRepository.save(nhanVien);
    }

    public NhanVien updateNhanVien(int id, NhanVien updatedNhanVien) {
        NhanVien existingNhanVien = nhanVienRepository.findById(id).orElseThrow(() -> new RuntimeException("NhanVien not found"));
        existingNhanVien.setIdVaiTro(updatedNhanVien.getIdVaiTro());
        existingNhanVien.setMa(updatedNhanVien.getMa());
        existingNhanVien.setTen(updatedNhanVien.getTen());
        existingNhanVien.setGioiTinh(updatedNhanVien.isGioiTinh());
        existingNhanVien.setNgaySinh(updatedNhanVien.getNgaySinh());
        existingNhanVien.setEmail(updatedNhanVien.getEmail());
        existingNhanVien.setSoDienThoai(updatedNhanVien.getSoDienThoai());
        existingNhanVien.setTaiKhoan(updatedNhanVien.getTaiKhoan());
        existingNhanVien.setMatKhau(updatedNhanVien.getMatKhau());
        existingNhanVien.setAnh(updatedNhanVien.getAnh());
        existingNhanVien.setNgaySua(java.time.LocalDate.now());
        existingNhanVien.setTrangThai(updatedNhanVien.getTrangThai());
        return nhanVienRepository.save(existingNhanVien);
    }
}