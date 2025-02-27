package com.example.dreambackend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaiTroResponse {

    private Integer id;               // ID vai trò
    private String ten;               // Tên vai trò
    private Integer trangThai;         // Trạng thái (Active/Inactive)
}
