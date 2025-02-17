package com.example.dreambackend.respones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThongKeThangResponse {
    private int thang;
    private double tongDoanhThu;
}
