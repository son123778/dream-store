package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.SanPham;
import com.example.dreambackend.respones.SanPhamRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    @Query("""
    select new com.example.dreambackend.respones.SanPhamRespone(
    sp.id,
    sp.ma,
    sp.ten,
    sp.ngayTao,
    sp.ngaySua,
    sp.trangThai,
    sp.chatLieu.ten,
    sp.thuongHieu.ten,
    sp.coAo.ten,
    sp.xuatXu.ten,
    sp.anh.anhUrl
        ) from SanPham sp
        """)
    Page<SanPhamRespone> getAllSanPhamRepone(Pageable pageable);
}
