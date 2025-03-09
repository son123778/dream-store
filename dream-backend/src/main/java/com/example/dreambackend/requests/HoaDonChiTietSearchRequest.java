package com.example.dreambackend.requests;

import lombok.Data;

import java.util.Date;

@Data
public class HoaDonChiTietSearchRequest {
    private Integer idHoaDon;
    private String maHoaDon;
    private String tenSanPham;
    private Double donGia;
    private Date ngayTaoTu;
    private Date ngayTaoDen;
    private Integer page;
    private Integer pageSize;
    private Integer totalRecords;
}
