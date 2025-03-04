package com.example.dreambackend.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHangSearchRequest {
    private Integer idKhachHang;
    private String mau;
    private String size;
    private String tenSanPham;
    private Integer page;
    private Integer pageSize;
    private Integer totalRecords;
}
