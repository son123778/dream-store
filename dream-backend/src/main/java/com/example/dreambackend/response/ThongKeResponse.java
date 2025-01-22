package com.example.dreambackend.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThongKeResponse {
    private long soHoaDon;
    private double tongDoanhThu;
    private long soKhachHang;
}
