package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.MauSac;
import com.example.dreambackend.responses.MauSacRepone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Integer> {
    @Query("""
    select new com.example.dreambackend.responses.MauSacRepone(
        ms.id,
        ms.ma,
        ms.ten,
        ms.ngayTao,
        ms.ngaySua,
        ms.trangThai
        )from MauSac ms
        """)
    List<MauSacRepone> getAllMauSacRepone();
}
