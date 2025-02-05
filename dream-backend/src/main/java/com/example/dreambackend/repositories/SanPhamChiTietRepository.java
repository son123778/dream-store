package com.example.dreambackend.repositories;

import com.example.dreambackend.dtos.SanPhamChiTietDto;
import com.example.dreambackend.entities.SanPhamChiTiet;
import com.example.dreambackend.respones.SanPhamChiTietRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        spct.size.id,
        spct.size.ten,
        spct.mauSac.id,
        spct.mauSac.ten
        ) from SanPhamChiTiet spct
    """)
    Page<SanPhamChiTietRespone> getAllSanPhamChiTietRespone(Pageable pageable);
    @Query("""
SELECT new com.example.dreambackend.dtos.SanPhamChiTietDto(
    spct.id,
    spct.ma,
    sp.ten,
    ms.ten,
    sz.ten,
    spct.gia,
    spct.soLuong,
    CASE WHEN spct.khuyenMai.id = :khuyenMaiId THEN true ELSE false END,
    CASE WHEN spct.khuyenMai.id IS NOT NULL AND spct.khuyenMai.id <> :khuyenMaiId THEN true ELSE false END
)
FROM SanPhamChiTiet spct
JOIN spct.sanPham sp
JOIN spct.mauSac ms
JOIN spct.size sz
WHERE spct.trangThai = 1
""")
    List<SanPhamChiTietDto> findAvailableProducts(@Param("khuyenMaiId") Integer khuyenMaiId);
    // Phương thức để tìm tất cả sản phẩm liên kết với một khuyến mãi cụ thể
    List<SanPhamChiTiet> findAllByKhuyenMaiId(Integer khuyenMaiId);
}
