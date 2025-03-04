package com.example.dreambackend.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDonSearchRequest {
    private String tenKhachHang;
    private String tenNhanVien;
    private String maHoaDon;
    private LocalDate ngayTaoFrom;
    private LocalDate ngayTaoTo;
    private List<Integer> listTrangThai;
    private Integer page;
    private Integer pageSize;
    private Integer totalRecords;
}
