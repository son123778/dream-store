package com.example.dreambackend.services.sanpham;

import com.example.dreambackend.entities.*;
import com.example.dreambackend.repositories.*;
import com.example.dreambackend.requests.SanPhamRequest;
import com.example.dreambackend.responses.SanPhamRespone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SanPhamService implements ISanPhamService {
    @Autowired
    SanPhamRepository sanPhamRepository;
    @Autowired
    ThuongHieuRepository thuongHieuRepository;
    @Autowired
    XuatXuRepository xuatXuRepository;
    @Autowired
    CoAoRepository coAoRepository;
    @Autowired
    ChatLieuRepository chatLieuRepository;
    @Override
    public Page<SanPhamRespone> getAllSanPham(Pageable pageable) {
        return sanPhamRepository.getAllSanPhamRepone(pageable);
    }

    @Override
    public SanPham addSanPham(SanPhamRequest sanPhamRequest) {
        SanPham sanPham = new SanPham();
        // Copy các thuộc tính cơ bản từ request sang entity
        BeanUtils.copyProperties(sanPhamRequest, sanPham);
        // Set ngày tạo và ngày sửa
        sanPham.setNgayTao(LocalDate.now());
        sanPham.setNgaySua(LocalDate.now());

        // Lưu sản phẩm
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
