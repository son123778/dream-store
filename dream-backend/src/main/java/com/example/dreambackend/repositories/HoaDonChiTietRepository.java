package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.HoaDon;
import com.example.dreambackend.entities.HoaDonChiTiet;

import com.example.dreambackend.entities.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    List<HoaDonChiTiet> findByHoaDonId(int id);

    Optional<HoaDonChiTiet> findByHoaDonAndSanPhamChiTiet(HoaDon hoaDon, SanPhamChiTiet sanPhamChiTiet);

    List<HoaDonChiTiet> findByHoaDon(HoaDon hoaDon);
}
