package com.example.dreambackend.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatLieuRequest {
    private Integer id;

    private String ma;

    private String ten;

    private LocalDate ngayTao;

    private LocalDate ngaySua;

    private int trangThai;
}
