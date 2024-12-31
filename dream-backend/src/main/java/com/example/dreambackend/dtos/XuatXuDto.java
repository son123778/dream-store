package com.example.dreambackend.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class XuatXuDto {
    private String ma;

    private String ten;

    @JsonProperty("ngay_tao")
    private LocalDate ngayTao;

    @JsonProperty("ngay_sua")
    private LocalDate ngaySua;

    @JsonProperty("trang_thai")
    private int trangThai;
}