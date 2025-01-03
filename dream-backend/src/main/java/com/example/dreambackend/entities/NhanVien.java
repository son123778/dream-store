package com.example.dreambackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

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

    @JsonProperty("vaiTroId")
    @Column(name = "id_vai_tro")
    private int idVaiTro;

    @JsonProperty("ten")
    @Column(name = "ten")
    private String ten;

    @JsonProperty("ngaySinh")
    @Column(name = "ngay_sinh")
    private String ngaySinh;

    @JsonProperty("gioiTinh")
    @Column(name = "gioi_tinh")
    private boolean gioiTinh;

    @JsonProperty("email")
    @Column(name = "email")
    private String email;

    @JsonProperty("soDienThoai")
    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @JsonProperty("taiKhoan")
    @Column(name = "tai_khoan")
    private String taiKhoan;

    @JsonProperty("matKhau")
    @Column(name = "mat_khau")
    private String matKhau;

}
