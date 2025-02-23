package com.example.dreambackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhan_vien")
public class NhanVien {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ma", nullable = false, length = 10)
    private String ma;  // Mã nhân viên

    @Column(name = "ten", nullable = false, length = 30)
    private String ten;  // Tên nhân viên

    @Column(name = "gioi_tinh", nullable = false)
    private Boolean gioiTinh;  // Giới tính

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;  // Ngày sinh

    @Column(name = "email", length = 50)
    private String email;  // Email

    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;  // Số điện thoại

    @Column(name = "tai_khoan", length = 30)
    private String taiKhoan;  // Tài khoản

    @Column(name = "mat_khau", length = 30)
    private String matKhau;  // Mật khẩu

    @Column(name = "anh", length = 255)
    private String anh;  // Đường dẫn ảnh nhân viên

    @Column(name = "ngay_tao", nullable = false)
    private LocalDate ngayTao;  // Ngày tạo bản ghi

    @Column(name = "ngay_sua")
    private LocalDate ngaySua;  // Ngày sửa bản ghi

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;  // Trạng thái nhân viên (1: Đang làm, 0: Nghỉ việc)

    @ManyToOne
    @JoinColumn(name = "id_vai_tro", referencedColumnName = "id", nullable = false) // Tham chiếu đến cột id của VaiTro
    private VaiTro vaiTro;  // Vai trò của nhân viên

}
