package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.SanPhamChiTiet;
import com.example.dreambackend.respones.SanPhamChiTietRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Integer> {
    @Query("""
    select new com.example.dreambackend.respones.SanPhamChiTietRespone(
        spct.id,
        spct.ma,
        spct.gia,
        spct.soLuong,
        spct.ngayTao,
        spct.ngaySua,
        spct.trangThai,
        spct.sanPham.id,
        spct.sanPham.ten,
        spct.mauSac.id,
        spct.mauSac.ten,
        spct.size.id,
        spct.size.ten
        ) from SanPhamChiTiet spct
    """)
    Page<SanPhamChiTietRespone> getAllSanPhamChiTietRespone(Pageable pageable);
}
