package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.HoaDonChiTiet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Integer> {

//    @Query("SELECT new com.example.dreambackend.responses.TopSanPhamResponse(sp.ten, SUM(hdct.soLuong)) " +
//            "FROM HoaDonChiTiet hdct " +
//            "JOIN hdct.sanPhamChiTiet spct " +
//            "JOIN spct.sanPham sp " +
//            "GROUP BY sp.ten " +
//            "ORDER BY SUM(hdct.soLuong) DESC")
//    List<TopSanPhamResponse> getTopSanPhamBanChay(Pageable pageable);
//

}
