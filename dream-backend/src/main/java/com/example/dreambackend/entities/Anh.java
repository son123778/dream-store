package com.example.dreambackend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "anh")
public class Anh {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "anh_url")
    private String anhUrl;

    @Column(name = "ngay_tao")
    private String ngayTao;

    @Column(name = "ngay_sua")
    private String ngaySua;

    @Column(name = "trang_thai")
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "id_san_pham",referencedColumnName = "id")
    private SanPham sanPham;
}
