package com.example.dreambackend.repositories;

import com.example.dreambackend.dtos.SanPhamDto;
import com.example.dreambackend.entities.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SanPhamOnlineRepository extends JpaRepository<SanPhamChiTiet, Integer> {
    @Query("SELECT new com.example.dreambackend.dtos.SanPhamDto(sp.id, sp.ten, spct.gia, a.anhUrl) " +
            "FROM SanPhamChiTiet spct " +
            "JOIN spct.sanPham sp " +
            "LEFT JOIN Anh a ON sp.id = a.sanPham.id " +
            "WHERE spct.trangThai = 1")
    List<SanPhamDto> getSanPhamChiTietOnline();
}
