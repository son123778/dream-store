package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.CoAo;
import com.example.dreambackend.respones.CoAoRespone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoAoRepository extends JpaRepository<CoAo, Integer> {
    @Query("""
    select new com.example.dreambackend.respones.CoAoRespone(
        ca.id,
        ca.ma,
        ca.ten,
        ca.ngayTao,
        ca.ngaySua,
        ca.trangThai
        )from CoAo ca
    """)
    List<CoAoRespone> getAllCoAoRespones();
}
