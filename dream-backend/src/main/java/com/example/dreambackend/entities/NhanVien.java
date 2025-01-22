package com.example.dreambackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhan_vien")
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_vai_tro")
    private int idVaiTro;

    @Column(name = "ma", nullable = false, length = 10)
    private String ma;

    @Column(name = "ten", length = 50)
    private String ten;

    @Column(name = "gioi_tinh")
    private boolean gioiTinh;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;

    @Column(name = "tai_khoan", length = 30)
    private String taiKhoan;

    @Column(name = "mat_khau", length = 30)
    private String matKhau;

    @Column(name = "anh", length = 100)
    private String anh;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "ngay_sua")
    private LocalDate ngaySua;

    @Column(name = "trang_thai")
    private int trangThai;
}