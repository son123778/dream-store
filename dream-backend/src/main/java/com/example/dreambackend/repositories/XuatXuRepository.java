package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.XuatXu;
import com.example.dreambackend.respones.XuatXuRespone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XuatXuRepository extends JpaRepository<XuatXu, Integer> {
    @Query("""
    select new com.example.dreambackend.respones.XuatXuRespone(
        xx.id,
        xx.ma,
        xx.ten,
        xx.ngayTao,
        xx.ngaySua,
        xx.trangThai
        ) from XuatXu xx
        """)
    List<XuatXuRespone> getAllXuatXuRespone();
}
