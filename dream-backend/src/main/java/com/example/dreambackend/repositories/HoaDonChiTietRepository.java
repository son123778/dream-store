package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.HoaDon;
import com.example.dreambackend.entities.HoaDonChiTiet;

import com.example.dreambackend.entities.SanPhamChiTiet;
import com.example.dreambackend.requests.HoaDonChiTietSearchRequest;
import com.example.dreambackend.responses.HoaDonChiTietResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
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

    default List<HoaDonChiTietResponse> search(HoaDonChiTietSearchRequest hoaDonChiTietSearchRequest, EntityManager entityManager) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("""
                SELECT
                    hdct.id,
                    hdct.id_hoa_don as idHoaDon,
                    spct.id as idSanPhamChiTiet,
                    hdct.ma as maHoaDonChiTiet,
                	sp.ten as tenSanPham,
                    hd.ma as maHoaDon,
                    hdct.ngay_tao as ngayTao,
                    hdct.ngay_sua as ngaySua,
                    hdct.don_gia as donGia,
                    hdct.so_luong as soLuong,
                    hdct.trang_thai as trangThai,
                    COUNT(1) OVER () AS totalRecords
                FROM hoa_don_chi_tiet hdct
                LEFT JOIN hoa_don hd ON hd.id = hdct.id_hoa_don
                LEFT JOIN san_pham_chi_tiet spct ON spct.id = hdct.id_san_pham_chi_tiet
                LEFT JOIN san_pham sp ON spct.id_san_pham = sp.id
                WHERE 1 = 1
                """);
        if (hoaDonChiTietSearchRequest.getIdHoaDon() != null) {
            sb.append(" AND hdct.id_hoa_don = :idHoaDon");
        }
        if (hoaDonChiTietSearchRequest.getMaHoaDon() != null && !hoaDonChiTietSearchRequest.getMaHoaDon().isEmpty()) {
            sb.append(" AND hd.ma = :maHoaDon");
        }
        if (hoaDonChiTietSearchRequest.getTenSanPham() != null && !hoaDonChiTietSearchRequest.getTenSanPham().isEmpty()) {
            sb.append(" AND UPPER(sp.ten) LIKE UPPeR(:tenSanPham)");
        }
        if (hoaDonChiTietSearchRequest.getDonGia() != null) {
            sb.append(" AND hdct.don_gia = :donGia");
        }
        if (hoaDonChiTietSearchRequest.getNgayTaoTu() != null) {
            sb.append(" AND CAST(hdct.ngay_tao AS DATE) >= :ngayTaoTu");
        }
        if (hoaDonChiTietSearchRequest.getNgayTaoDen() != null) {
            sb.append(" AND CAST(hdct.ngay_tao AS DATE) >= :ngayTaoDen");
        }

        sb.append(" ORDER BY hdct.ngay_tao DESC, hdct.ngay_sua DESC");

        Query query = entityManager.createNativeQuery(sb.toString(), "HoaDonChiTietResponseMapping");
        if (hoaDonChiTietSearchRequest.getPage() != null && hoaDonChiTietSearchRequest.getPageSize() != null) {
            query.setFirstResult((hoaDonChiTietSearchRequest.getPage() - 1) * hoaDonChiTietSearchRequest.getPageSize());
            query.setMaxResults(hoaDonChiTietSearchRequest.getPageSize());
        }
        if (hoaDonChiTietSearchRequest.getIdHoaDon() != null) {
            query.setParameter("idHoaDon", hoaDonChiTietSearchRequest.getIdHoaDon());
        }
        if (hoaDonChiTietSearchRequest.getMaHoaDon() != null && !hoaDonChiTietSearchRequest.getMaHoaDon().isEmpty()) {
            query.setParameter("maHoaDon", hoaDonChiTietSearchRequest.getMaHoaDon());
        }
        if (hoaDonChiTietSearchRequest.getTenSanPham() != null && !hoaDonChiTietSearchRequest.getTenSanPham().isEmpty()) {
            query.setParameter("tenSanPham", "%" + hoaDonChiTietSearchRequest.getTenSanPham() + "%");
        }
        if (hoaDonChiTietSearchRequest.getDonGia() != null && hoaDonChiTietSearchRequest.getDonGia() > 0) {
            query.setParameter("donGia", hoaDonChiTietSearchRequest.getDonGia());
        }
        if (hoaDonChiTietSearchRequest.getNgayTaoTu() != null) {
            query.setParameter("ngayTaoTu",dateFormat.format(hoaDonChiTietSearchRequest.getNgayTaoTu()));
        }
        if (hoaDonChiTietSearchRequest.getNgayTaoDen() != null) {
            query.setParameter("ngayTaoDen",dateFormat.format(hoaDonChiTietSearchRequest.getNgayTaoDen()));
        }
        List<HoaDonChiTietResponse> list = query.getResultList();
        if (list.isEmpty()){
            hoaDonChiTietSearchRequest.setTotalRecords(0);
        }else {
            hoaDonChiTietSearchRequest.setTotalRecords(list.get(0).getTotalRecords());
        }
        return list;
    }
}
