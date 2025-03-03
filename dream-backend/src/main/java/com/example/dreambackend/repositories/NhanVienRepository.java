package com.example.dreambackend.repositories;

import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.responses.NhanVienResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {

    // Trả về danh sách NhanVienResponse
    @Query("""
        SELECT new com.example.dreambackend.responses.NhanVienResponse(
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
        SELECT new com.example.dreambackend.responses.NhanVienResponse(
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
        SELECT new com.example.dreambackend.responses.NhanVienResponse(
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
    // Tìm nhân viên dựa trên email
    Optional<NhanVien> findByEmail(String email);
    NhanVien findByTaiKhoan(String taiKhoan);
}
