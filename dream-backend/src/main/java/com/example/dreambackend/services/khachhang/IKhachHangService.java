package com.example.dreambackend.services.khachhang;

import com.example.dreambackend.dtos.KhachHangDto;
import com.example.dreambackend.entities.KhachHang;

import java.util.List;

public interface IKhachHangService {
    List<KhachHang> getAllKhachHang();
    KhachHang getKhachHangById(Integer id);
    KhachHang newKhachHang(KhachHangDto khachHangDto);
    KhachHang updateKhachHang(Integer id,KhachHangDto khachHangDto);
}
