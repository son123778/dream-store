package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.HoaDonChiTiet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonChiTietRepository extends CrudRepository<HoaDonChiTiet, Integer> {

//    @Query("SELECT new com.example.dreambackend.respones.TopSanPhamResponse(sp.ten, SUM(hdct.soLuong)) " +
//            "FROM HoaDonChiTiet hdct " +
//            "JOIN hdct.sanPhamChiTiet spct " +
//            "JOIN spct.sanPham sp " +
//            "GROUP BY sp.ten " +
//            "ORDER BY SUM(hdct.soLuong) DESC")
//    List<TopSanPhamResponse> getTopSanPhamBanChay(Pageable pageable);
//

}
