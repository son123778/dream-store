package com.example.dreambackend.services.sanphamchitiet;

import com.example.dreambackend.entities.SanPhamChiTiet;
import com.example.dreambackend.repositories.SanPhamChiTietRepository;
import com.example.dreambackend.requests.SanPhamChiTietRequest;
import com.example.dreambackend.responses.SanPhamChiTietRespone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SanPhamChiTietService implements ISanPhamChiTietService {
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Override
    public Page<SanPhamChiTietRespone> getAllSanPhamChiTiet(Pageable pageable) {
        return sanPhamChiTietRepository.getAllSanPhamChiTietRespone(pageable);
    }

    @Override
    public SanPhamChiTiet getsanPhamChiTietById(Integer id) {
        return sanPhamChiTietRepository.findById(id).orElseThrow(()->
                new RuntimeException("Không tìm thấy id sản phẩm chi tiết"));
    }

    @Override
    public SanPhamChiTiet addSanPhamChiTiet(SanPhamChiTietRequest sanPhamChiTietRequest) {
        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
        BeanUtils.copyProperties(sanPhamChiTietRequest, sanPhamChiTiet);
        sanPhamChiTiet.setNgayTao(LocalDate.now());
        sanPhamChiTiet.setNgaySua(LocalDate.now());
        return sanPhamChiTietRepository.save(sanPhamChiTiet);
    }

    @Override
    public SanPhamChiTiet updateSanPhamChiTiet(SanPhamChiTietRequest sanPhamChiTietRequest) {
        SanPhamChiTiet sanphamChiTietUpdate = sanPhamChiTietRepository.findById(sanPhamChiTietRequest.getId()).orElseThrow(()->
                new RuntimeException("Không tìm thấy sản phẩm chi tiết với id: "+sanPhamChiTietRequest.getId()));
        BeanUtils.copyProperties(sanPhamChiTietRequest, sanphamChiTietUpdate,"id","ngayTao");
        sanphamChiTietUpdate.setNgaySua(LocalDate.now());
        return sanPhamChiTietRepository.save(sanphamChiTietUpdate);
    }
}
