package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.GioHangChiTiet;
import com.example.dreambackend.entities.KhachHang;
import com.example.dreambackend.entities.SanPhamChiTiet;
import com.example.dreambackend.requests.GioHangSearchRequest;
import com.example.dreambackend.responses.GioHangChiTietResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet,Integer> {
    List<GioHangChiTiet> findByKhachHangAndSanPhamChiTiet(KhachHang khachHang, SanPhamChiTiet sanPhamChiTiet);


    List<GioHangChiTiet> findByKhachHang(KhachHang khachHang);

    default List<GioHangChiTietResponse> search(GioHangSearchRequest request, EntityManager entityManager) {
        StringBuilder sql = new StringBuilder();
        sql.append("""
                SELECT
                	ghct.id,
                	ghct.so_luong as soLuong,
                	ghct.don_gia AS donGia,
                	a.anh_url AS anhUrl,
                	sp.ten AS tenSanPham,
                	km.hinh_thuc_giam as hinhThucGiam,
                 	km.gia_tri_giam as giaTriGiam,
                	ms.ten AS mau,
                	sz.ten AS size,
                	ghct.ngay_tao AS ngayTao,
                	ghct.ngay_sua AS ngaySua,
                	ghct.trang_thai AS trangThai,
                	COUNT (1) OVER () AS totalRecords
                FROM gio_hang_chi_tiet ghct
                LEFT JOIN san_pham_chi_tiet spct ON spct.id = ghct.id_san_pham_chi_tiet
                LEFT JOIN san_pham sp ON sp.id = spct.id_san_pham
                LEFT JOIN mau_sac ms ON ms.id = spct.id_mau_sac
                LEFT JOIN SIZE sz ON sz.id = spct.id_size
                LEFT JOIN khach_hang kh ON kh.id = ghct.id_khach_hang
                LEFT JOIN anh a ON sp.id = a.id_san_pham
                LEFT JOIN khuyen_mai km ON km.id = spct.id_khuyen_mai
                WHERE 1=1
                """);
        if (request.getMau() != null && !request.getMau().isEmpty()) {
            sql.append(" AND ms.ma = :mau");
        }
        if (request.getSize() != null && !request.getSize().isEmpty()) {
            sql.append(" AND sz.ma = :size");
        }
        if (request.getTenSanPham() != null && !request.getTenSanPham().isEmpty()) {
            sql.append(" AND UPPER(tenSanPham) = UPPER(:tenSanPham)");
        }
        if (request.getIdKhachHang() != null) {
            sql.append(" AND kh.id = :idKhachHang");
        }
        sql.append(" ORDER BY ghct.id DESC");

        Query query = entityManager.createNativeQuery(sql.toString(),"GioHangChiTietResponseMapping");

        if (request.getMau() != null && !request.getMau().isEmpty()) {
            query.setParameter("mau", request.getMau());
        }
        if (request.getSize() != null && !request.getSize().isEmpty()) {
            query.setParameter("size", request.getSize());
        }
        if (request.getTenSanPham() != null && !request.getTenSanPham().isEmpty()) {
            query.setParameter("tenSanPham", "%" + request.getTenSanPham() + "%");
        }
        if (request.getIdKhachHang() != null) {
            query.setParameter("idKhachHang", request.getIdKhachHang());
        }
        if (request.getPage()!=null && request.getPageSize()!=null) {
            query.setFirstResult((request.getPage()-1)*request.getPageSize());
            query.setMaxResults(request.getPageSize());
        }

        List<GioHangChiTietResponse> result = query.getResultList();
        if (result.isEmpty()) {
            request.setPageSize(0);
        }else{
            request.setPageSize(result.get(0).getTotalRecords());
        }
        return result;
    }
}
