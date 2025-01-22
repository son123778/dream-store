package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {

    List<NhanVien> findByTrangThai(int trangThai);

    @Query("SELECT n FROM NhanVien n WHERE n.ten LIKE %:keyword% OR n.email LIKE %:keyword% OR n.soDienThoai LIKE %:keyword%")
    List<NhanVien> findByKeyword(String keyword);

    @Query("SELECT n FROM NhanVien n WHERE n.trangThai = :trangThai AND (n.ten LIKE %:keyword% OR n.email LIKE %:keyword% OR n.soDienThoai LIKE %:keyword%)")
    List<NhanVien> findByTrangThaiAndKeyword(int trangThai, String keyword);
}
