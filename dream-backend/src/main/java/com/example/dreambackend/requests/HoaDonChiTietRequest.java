package com.example.dreambackend.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDonChiTietRequest {
    private Integer IdHoaDon;
    private Integer sanPhamChiTietId;
    private String ma;
    private Integer soLuong;
    private Double donGia;
    private String ngayTao;
    private String ngaySua;
    private Integer trangThai;
}
