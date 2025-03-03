package com.example.dreambackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietOnlineDto {
    private Integer idSanPhamChiTiet; // ID của SanPhamChiTiet
    private String tenSanPham;
    private String anhUrl; // Đổi lại đúng tên field trong DB
    private Double gia;
    private String tenChatLieu;
    private String tenCoAo;
    private String tenThuongHieu;
    private String tenXuatXu;
    private String tenMauSac;
    private String tenSize;
    private Integer soLuongSanPham;
    private Integer soLuongGioHang;
}
