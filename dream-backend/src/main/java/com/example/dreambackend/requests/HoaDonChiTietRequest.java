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
    private Integer idHoaDon;
    private Integer idSanPhamChiTiet;
    private Integer soLuong;
    private Double donGia;
    private LocalDate ngayTao;
    private LocalDate ngaySua;
    private Integer trangThai;
}
