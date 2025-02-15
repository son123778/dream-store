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
public class HoaDonResponse {
    private Integer id;
    private Integer idKhachHang;
    private Integer idNhanVien;
    private Integer idVoucher;
    private Integer idPhuongThucThanhToan;
    private String tenNguoiNhan;
    private String sdtNguoiNhan;
    private String diaChiNhanHang;
    private String hinhThucThanhToan;
    private Float phiVanChuyen;
    private Float tongTienTruocVoucher;
    private Float tongTienThanhToan;
    private LocalDate ngayNhanDuKien;
    private LocalDate ngayTao;
    private LocalDate ngaySua;
    private String trangThai;
    private String ghiChu;
}
