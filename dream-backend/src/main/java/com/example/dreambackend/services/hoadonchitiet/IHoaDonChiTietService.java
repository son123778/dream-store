package com.example.dreambackend.services.hoadonchitiet;

import com.example.dreambackend.requests.HoaDonChiTietRequest;
import com.example.dreambackend.requests.HoaDonChiTietSearchRequest;
import com.example.dreambackend.responses.HoaDonChiTietResponse;

import java.util.List;

public interface IHoaDonChiTietService {
    HoaDonChiTietResponse addSanPhamToHoaDon(HoaDonChiTietRequest hoaDonChiTietRequest);

    HoaDonChiTietResponse updateHoaDonChiTiet(Integer id, Integer soLuong);

    List<HoaDonChiTietResponse> search(HoaDonChiTietSearchRequest searchRequest);
}
