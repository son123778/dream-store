package com.example.dreambackend.services.sanpham;

import com.example.dreambackend.entities.*;
import com.example.dreambackend.repositories.*;
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
import java.util.Optional;

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

        // Xử lý thuộc tính ChatLieu
        if (sanPhamRequest.getChatLieu() != null && sanPhamRequest.getChatLieu().getId() != null) {
            Optional<ChatLieu> chatLieuOpt = chatLieuRepository.findById(sanPhamRequest.getChatLieu().getId());
            if (chatLieuOpt.isPresent()) {
                sanPham.setChatLieu(chatLieuOpt.get());
            } else {
                throw new IllegalArgumentException("ChatLieu không tồn tại!");
            }
        }

        // Xử lý thuộc tính ThuongHieu
        if (sanPhamRequest.getThuongHieu() != null && sanPhamRequest.getThuongHieu().getId() != null) {
            Optional<ThuongHieu> thuongHieuOpt = thuongHieuRepository.findById(sanPhamRequest.getThuongHieu().getId());
            if (thuongHieuOpt.isPresent()) {
                sanPham.setThuongHieu(thuongHieuOpt.get());
            } else {
                throw new IllegalArgumentException("ThuongHieu không tồn tại!");
            }
        }

        // Xử lý thuộc tính CoAo
        if (sanPhamRequest.getCoAo() != null && sanPhamRequest.getCoAo().getId() != null) {
            Optional<CoAo> coAoOpt = coAoRepository.findById(sanPhamRequest.getCoAo().getId());
            if (coAoOpt.isPresent()) {
                sanPham.setCoAo(coAoOpt.get());
            } else {
                throw new IllegalArgumentException("CoAo không tồn tại!");
            }
        }

        // Xử lý thuộc tính XuatXu
        if (sanPhamRequest.getXuatXu() != null && sanPhamRequest.getXuatXu().getId() != null) {
            Optional<XuatXu> xuatXuOpt = xuatXuRepository.findById(sanPhamRequest.getXuatXu().getId());
            if (xuatXuOpt.isPresent()) {
                sanPham.setXuatXu(xuatXuOpt.get());
            } else {
                throw new IllegalArgumentException("XuatXu không tồn tại!");
            }
        }

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
