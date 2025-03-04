package com.example.dreambackend.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDonChiTietSearchRequest {
    private Integer idHoaDon;
    private String maHoaDon;
    private String tenSanPham;
    private Double donGia;
    private String ngayTaoTu;
    private String ngayTaoDen;
    private Integer trangThai;
    private Integer page;
    private Integer pageSize;
    private Integer totalRecords;
}
