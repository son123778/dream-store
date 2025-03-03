package com.example.dreambackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String ma;

    @Column(name = "ten", nullable = false, length = 30)
    private String ten;

    @Column(name = "gioi_tinh", nullable = false)
    private Boolean gioiTinh;

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

    @Column(name = "anh", length = 50)
    private String anh;

    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime ngayTao;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "id_vai_tro", referencedColumnName = "id", nullable = false) // Tham chiếu đến cột id của VaiTro
    @JsonBackReference
    private VaiTro vaiTro;
}
