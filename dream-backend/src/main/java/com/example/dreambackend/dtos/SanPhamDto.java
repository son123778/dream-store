package com.example.dreambackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDto {
    private Integer idSanPham;
    private String tenSanPham;
    private Double gia;
    private String anhUrl;
}
