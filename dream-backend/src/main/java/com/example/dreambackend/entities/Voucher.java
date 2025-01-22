package com.example.dreambackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ma", nullable = false, unique = true)
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "hinh_thuc_giam")
    private Boolean hinhThucGiam; // True for percentage, false for fixed amount

    @Column(name = "gia_tri_giam")
    private Float giaTriGiam;

    @Column(name = "don_toi_thieu")
    private Float donToiThieu;

    @Column(name = "giam_toi_da")
    private Float giamToiDa;

    @Column(name = "thoi_gian_bat_dau")
    private LocalDateTime thoiGianBatDau;

    @Column(name = "thoi_gian_ket_thuc")
    private LocalDateTime thoiGianKetThuc;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "trang_thai")
    private Integer trangThai;
}
