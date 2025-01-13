package com.example.dreambackend.services.sanphamchitiet;

import com.example.dreambackend.entities.SanPhamChiTiet;
import com.example.dreambackend.requests.SanPhamChiTietRequest;
import com.example.dreambackend.respones.SanPhamChiTietRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISanPhamChiTiet {
    Page<SanPhamChiTietRespone> getAllSanPhamChiTiet(Pageable pageable);

    SanPhamChiTiet getsanPhamChiTietById(Integer id);

    SanPhamChiTiet addSanPhamChiTiet(SanPhamChiTietRequest sanPhamChiTietRequest);

    SanPhamChiTiet updateSanPhamChiTiet(SanPhamChiTietRequest sanPhamChiTietRequest);
}
