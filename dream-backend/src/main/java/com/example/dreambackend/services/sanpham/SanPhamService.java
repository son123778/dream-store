package com.example.dreambackend.services.sanpham;

import com.example.dreambackend.entities.SanPham;
import com.example.dreambackend.repositories.SanPhamRepository;
import com.example.dreambackend.requests.SanPhamRequest;
import com.example.dreambackend.respones.SanPhamRespone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class SanPhamService implements ISanPhamService {
    @Autowired
    SanPhamRepository sanPhamRepository;
    @Override
    public Page<SanPhamRespone> getAllSanPham(Pageable pageable) {
        return sanPhamRepository.getAllSanPhamRepone(pageable);
    }

    @Override
    public SanPham addSanPham(SanPhamRequest sanPhamRequest) {
        SanPham sanPham = new SanPham();
        BeanUtils.copyProperties(sanPhamRequest, sanPham);
        sanPham.setNgayTao(LocalDate.now());
        sanPham.setNgaySua(LocalDate.now());
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham updateSanPham(SanPhamRequest sanPhamRequest) {
        SanPham sanPhamUpdate = sanPhamRepository.findById(sanPhamRequest.getId()).orElseThrow(()->
                new RuntimeException("Không tìm thấy sản phẩm với id: " + sanPhamRequest.getId()));
        BeanUtils.copyProperties(sanPhamRequest, sanPhamUpdate,"id","ngayTao");
        sanPhamUpdate.setNgaySua(LocalDate.now());
        return sanPhamRepository.save(sanPhamUpdate);
    }

    @Override
    public SanPham getSanPhamById(Integer id) {
        return sanPhamRepository.findById(id).orElseThrow(()->
                new RuntimeException("Không tìm thấy id sản phẩm"));
    }
}
