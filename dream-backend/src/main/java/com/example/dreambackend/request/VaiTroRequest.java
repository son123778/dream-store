package com.example.dreambackend.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaiTroRequest {
    private Integer id;               // ID vai trò (bắt buộc khi Update, không cần khi Create)
    private String ten;               // Tên vai trò
    private Integer trangThai;         // Trạng thái (Active/Inactive)
}
