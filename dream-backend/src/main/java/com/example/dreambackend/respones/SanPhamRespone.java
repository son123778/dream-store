package com.example.dreambackend.respones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}

