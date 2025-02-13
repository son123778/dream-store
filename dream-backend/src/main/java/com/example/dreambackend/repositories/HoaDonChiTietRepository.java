package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.HoaDonChiTiet;
import com.example.dreambackend.response.TopSanPhamResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends CrudRepository<HoaDonChiTiet, Integer> {

//    @Query("SELECT new com.example.dreambackend.response.TopSanPhamResponse(sp.ten, SUM(hdct.soLuong)) " +
//            "FROM HoaDonChiTiet hdct " +
//            "JOIN hdct.sanPhamChiTiet spct " +
//            "JOIN spct.sanPham sp " +
//            "GROUP BY sp.ten " +
//            "ORDER BY SUM(hdct.soLuong) DESC")
//    List<TopSanPhamResponse> getTopSanPhamBanChay(Pageable pageable);
//

}
