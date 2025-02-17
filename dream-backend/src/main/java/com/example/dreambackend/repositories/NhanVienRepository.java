package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.respones.NhanVienResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {

    // Trả về danh sách NhanVienResponse
    @Query("""
        SELECT new com.example.dreambackend.respones.NhanVienResponse(
            nv.id,
            nv.ma,
            nv.ten,
            nv.gioiTinh,
            nv.ngaySinh,
            nv.email,
            nv.soDienThoai,
            nv.taiKhoan,
            nv.matKhau,
            nv.anh,
            nv.ngayTao,
            nv.ngaySua,
            nv.trangThai,
            nv.vaiTro.ten
        )
        FROM NhanVien nv
    """)
    List<NhanVienResponse> getAllNhanVienResponses();

    // Tìm kiếm nhân viên theo tên
    @Query("""
        SELECT new com.example.dreambackend.respones.NhanVienResponse(
            nv.id,
            nv.ma,
            nv.ten,
            nv.gioiTinh,
            nv.ngaySinh,
            nv.email,
            nv.soDienThoai,
            nv.taiKhoan,
            nv.matKhau,
            nv.anh,
            nv.ngayTao,
            nv.ngaySua,
            nv.trangThai,
            nv.vaiTro.ten
        )
        FROM NhanVien nv
        WHERE LOWER(nv.ten) LIKE LOWER(CONCAT('%', :ten, '%'))
    """)
    List<NhanVienResponse> searchNhanVienByName(String ten);

    // Lọc nhân viên theo trạng thái
    @Query("""
        SELECT new com.example.dreambackend.respones.NhanVienResponse(
            nv.id,
            nv.ma,
            nv.ten,
            nv.gioiTinh,
            nv.ngaySinh,
            nv.email,
            nv.soDienThoai,
            nv.taiKhoan,
            nv.matKhau,
            nv.anh,
            nv.ngayTao,
            nv.ngaySua,
            nv.trangThai,
            nv.vaiTro.ten
        )
        FROM NhanVien nv
        WHERE nv.trangThai = :trangThai
    """)
    List<NhanVienResponse> filterNhanVienByTrangThai(Integer trangThai);
}
