package com.example.dreambackend.services.giohangchitiet;

import com.example.dreambackend.requests.GioHangChiTietRequest;
import com.example.dreambackend.requests.GioHangSearchRequest;
import com.example.dreambackend.responses.GioHangChiTietResponse;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface IGioHangChiTietService {
    GioHangChiTietResponse addToGioHang(Integer khachHangId, GioHangChiTietRequest request);
    GioHangChiTietResponse updateSoLuong(Integer id, Integer soLuong);
    void removeFromGioHang(Integer id);
    List<GioHangChiTietResponse> getGioHangByKhachHang(Integer khachHangId);
    void clearGioHang(Integer khachHangId);

    List<GioHangChiTietResponse> search(GioHangSearchRequest request);
}
