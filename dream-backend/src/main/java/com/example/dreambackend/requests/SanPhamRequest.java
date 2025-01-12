package com.example.dreambackend.requests;

import com.example.dreambackend.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamRequest {
    private Integer id;

    private String ma;

    private String ten;

    private LocalDate ngayTao;

    private LocalDate ngaySua;

    private int trangThai;

    private ChatLieu chatLieu;

    private ThuongHieu thuongHieu;

    private CoAo coAo;

    private XuatXu xuatXu;
}
