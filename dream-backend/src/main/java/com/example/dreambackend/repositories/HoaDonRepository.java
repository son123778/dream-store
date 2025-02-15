package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.HoaDon;
import com.example.dreambackend.responses.ThongKeResponse;
import com.example.dreambackend.responses.ThongKeThangResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {

    @Query("SELECT new com.example.dreambackend.responses.ThongKeResponse(COUNT(h.id), SUM(h.tongTienThanhToan), COUNT(DISTINCT h.khachHang.id)) " +
            "FROM HoaDon h " +
            "WHERE (:startDate IS NULL OR h.ngayNhanDuKien >= :startDate) AND (:endDate IS NULL OR h.ngayNhanDuKien <= :endDate)")
    ThongKeResponse getTongQuan(LocalDate startDate, LocalDate endDate);

    @Query("SELECT new com.example.dreambackend.responses.ThongKeThangResponse(MONTH(h.ngayNhanDuKien), SUM(h.tongTienThanhToan)) " +
            "FROM HoaDon h " +
            "WHERE YEAR(h.ngayNhanDuKien) = YEAR(CURRENT_DATE) " +
            "GROUP BY MONTH(h.ngayNhanDuKien) " +
            "ORDER BY MONTH(h.ngayNhanDuKien)")
    List<ThongKeThangResponse> getDoanhThuTungThang();
    @Query("SELECT YEAR(h.ngayNhanDuKien) AS year, SUM(h.tongTienThanhToan) AS totalRevenue " +
            "FROM HoaDon h " +
            "GROUP BY YEAR(h.ngayNhanDuKien) " +
            "ORDER BY YEAR(h.ngayNhanDuKien)")
    List<Object[]> getDoanhThuTungNam();
}
