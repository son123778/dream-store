package com.example.dreambackend.repositories;

import com.example.dreambackend.dtos.SanPhamDto;
import com.example.dreambackend.entities.SanPham;
import com.example.dreambackend.entities.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
    public interface SanPhamOnlineRepository extends JpaRepository<SanPham, Integer> {
    @Query("SELECT new com.example.dreambackend.dtos.SanPhamDto(sp.id, sp.ten, " +
            "(SELECT MIN(spct.gia) FROM SanPhamChiTiet spct WHERE spct.sanPham.id = sp.id), " + // Lấy giá nhỏ nhất nếu có nhiều biến thể
            "(SELECT a.anhUrl FROM Anh a WHERE a.sanPham.id = sp.id ORDER BY a.id ASC LIMIT 1)) " + // Lấy ảnh đầu tiên của sản phẩm
            "FROM SanPham sp " +
            "WHERE sp.trangThai = 1 " +
            "ORDER BY sp.id DESC")
    List<SanPhamDto> getSanPhamChiTietOnline();
    }
