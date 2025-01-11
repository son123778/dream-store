package com.example.dreambackend.respones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamRespone {
    private Integer id;

    private String ma;

    private String ten;

    private LocalDate ngayTao;

    private LocalDate ngaySua;

    private int trangThai;

    private Integer idChatLieu;

    private String tenChatLieu;

    private Integer idThuongHieu;

    private String tenThuongHieu;

    private Integer idCoAo;

    private String tenCoAo;

    private Integer idXuatXu;

    private String tenXuatXu;

    private Integer idAnh;

    private String anhUrl;
}
