package com.example.dreambackend.services.sanphamonline;

import com.example.dreambackend.dtos.SanPhamDto;
import com.example.dreambackend.repositories.SanPhamOnlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SanPhamOnlineService implements ISanPhamOnlineService {
    @Autowired
    SanPhamOnlineRepository sanPhamOnlineRepository;
    @Override
    public List<SanPhamDto> getSanPhamOnline() {
        return sanPhamOnlineRepository.getSanPhamChiTietOnline();
    }
}
