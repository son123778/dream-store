package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.Anh;
import com.example.dreambackend.respones.AnhRespone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnhRepository extends JpaRepository<Anh, Integer> {
    @Query("""
    select new com.example.dreambackend.respones.AnhRespone(
        a.id,
        a.anhUrl,
        a.ngayTao,
        a.ngaySua,
        a.trangThai,
        a.sanPham.id,
        a.sanPham.ten
        ) from Anh a
        """)
    List<AnhRespone> getAllAnhRespones();
}
