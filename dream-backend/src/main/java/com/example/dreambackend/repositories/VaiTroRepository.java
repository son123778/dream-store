package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.VaiTro;
import com.example.dreambackend.respones.VaiTroResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, Integer> {

    // Trả về danh sách VaiTroResponse
    @Query("""
        SELECT new com.example.dreambackend.respones.VaiTroResponse(
            vt.id,
            vt.ten,
            vt.trangThai
        )
        FROM VaiTro vt
    """)
    List<VaiTroResponse> getAllVaiTroResponses();

    // Tìm kiếm VaiTrò theo tên
    @Query("""
        SELECT new com.example.dreambackend.respones.VaiTroResponse(
            vt.id,
            vt.ten,
            vt.trangThai
        )
        FROM VaiTro vt
        WHERE LOWER(vt.ten) LIKE LOWER(CONCAT('%', :ten, '%'))
    """)
    List<VaiTroResponse> searchVaiTroByName(String ten);
}
