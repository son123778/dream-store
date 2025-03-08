package com.example.dreambackend.services.khachhang;

import com.example.dreambackend.dtos.KhachHangDto;
import com.example.dreambackend.entities.KhachHang;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IKhachHangService {
    KhachHang getKhachHangById(Integer id);
    KhachHang addKhachHang(KhachHangDto khachHangDto);
    KhachHang updateKhachHang(KhachHang khachHang);
    Page<KhachHang> getAllKhachHangPaged(int page, int size);
    List<KhachHang> searchKhachHangByName(String ten);
    KhachHang getKhachHangByEmail(String email);
    KhachHang updateOtpKhachHang(String email);
    KhachHang deleteOtpKhachHang(String email);
    KhachHang compareOtp(String email,String otp);
}
