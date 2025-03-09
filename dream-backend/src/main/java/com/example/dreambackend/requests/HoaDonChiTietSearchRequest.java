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
public class HoaDonChiTietSearchRequest {
    private Integer idHoaDon;
    private String maHoaDon;
    private String tenSanPham;
    private Double donGia;
    private LocalDate ngayTaoTu;
    private LocalDate ngayTaoDen;
    private Integer trangThai;
    private Integer page;
    private Integer pageSize;
    private Integer totalRecords;
}
