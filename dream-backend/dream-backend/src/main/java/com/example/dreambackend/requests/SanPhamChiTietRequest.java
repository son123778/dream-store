package com.example.dreambackend.requests;

import com.example.dreambackend.entities.KhuyenMai;
import com.example.dreambackend.entities.MauSac;
import com.example.dreambackend.entities.SanPham;
import com.example.dreambackend.entities.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamChiTietRequest {
    private Integer id;

    private String ma;

    private Double gia;

    private int soLuong;

    private LocalDate ngayTao;

    private LocalDate ngaySua;

    private int trangThai;

    private SanPham sanPham;

    private Size size;

    private MauSac mauSac;
}
