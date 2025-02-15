package com.example.dreambackend.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoaDonChiTietResponse {
    private Integer IdHoaDon;
    private Integer sanPhamChiTietId;
    private String ma;
    private Integer soLuong;
    private Double donGia;
    private LocalDate ngayTao;
    private LocalDate ngaySua;
    private Integer trangThai;
}
