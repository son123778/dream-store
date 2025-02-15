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
public class GioHangChiTietResponse {
    private Integer id;

    private Integer soLuong;

    private Double donGia;

    private LocalDate ngayTao;

    private LocalDate ngaySua;

    private Integer trangThai;

    private Integer idKhachHang;

    private Integer idSanPhamChiTiet;
}
