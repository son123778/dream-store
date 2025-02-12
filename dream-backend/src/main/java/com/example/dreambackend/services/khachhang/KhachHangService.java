package com.example.dreambackend.services.khachhang;

import com.example.dreambackend.dtos.KhachHangDto;
import com.example.dreambackend.entities.KhachHang;
import com.example.dreambackend.repositories.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class KhachHangService implements IKhachHangService{
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang getKhachHangById(Integer id) {
        return khachHangRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Không tim được id cua khach hang"));
    }

    @Override
    public KhachHang newKhachHang(KhachHangDto khachHangDto) {
        KhachHang newKhachHang = KhachHang.builder()
                .ma(khachHangDto.getMa())
                .ten(khachHangDto.getTen())
                .gioiTinh(khachHangDto.isGioiTinh())
                .soDienThoai(khachHangDto.getSoDienThoai())
                .matKhau(khachHangDto.getMatKhau())
                .ngayTao(LocalDate.now())
                .trangThai(khachHangDto.getTrangThai())
                .build();
                return  khachHangRepository.save(newKhachHang);


    }

    @Override
    public KhachHang updateKhachHang(Integer id,KhachHangDto updateKhachHangDto) {
        KhachHang updatedkh = getKhachHangById(id);
        updatedkh.setTen(updateKhachHangDto.getTen());
        updatedkh.setGioiTinh(updateKhachHangDto.isGioiTinh());
        updatedkh.setMatKhau(updateKhachHangDto.getMatKhau());
        updatedkh.setSoDienThoai(updateKhachHangDto.getSoDienThoai());
        updatedkh.setNgaySua(LocalDate.now());
        updatedkh.setTrangThai(updateKhachHangDto.getTrangThai());
        khachHangRepository.save(updatedkh);
        return updatedkh;
    }


}
