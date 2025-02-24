package com.example.dreambackend.services.khuyenmai;

import com.example.dreambackend.dtos.SanPhamChiTietDto;
import com.example.dreambackend.entities.KhuyenMai;
import com.example.dreambackend.entities.SanPhamChiTiet;
import com.example.dreambackend.repositories.KhuyenMaiRepository;
import com.example.dreambackend.repositories.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class KhuyenMaiService implements IKhuyenMaiService{
    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;
    @Override
    public Page<KhuyenMai> getAllKhuyenMaiPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<KhuyenMai> khuyenMais = khuyenMaiRepository.findAll(pageable);
        // Kiểm tra và cập nhật trạng thái khuyến mãi nếu cần
        khuyenMais.getContent().forEach(this::checkAndUpdateStatus);
        return khuyenMais;
    }
    @Override
    public KhuyenMai addKhuyenMai(KhuyenMai khuyenMai) {
        khuyenMai.setNgayTao(LocalDate.now());
        return khuyenMaiRepository.save(khuyenMai);
    }
    @Override
    public KhuyenMai updateKhuyenMai(KhuyenMai khuyenMai) {
        khuyenMai.setNgaySua(LocalDate.now());
        return khuyenMaiRepository.save(khuyenMai);
    }
    @Override
    public KhuyenMai getKhuyenMaiById(Integer id) {
        return khuyenMaiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khuyến mãi không tồn tại với id: " + id));
    }
    @Override
    public List<KhuyenMai> searchKhuyenMaiByName(String ten) {
        return khuyenMaiRepository.findByTenContainingIgnoreCase(ten);
    }

    @Override
    public List<SanPhamChiTietDto> findAvailableProducts(Integer khuyenMaiId) {
        return sanPhamChiTietRepository.findAvailableProducts(khuyenMaiId);
    }

    @Override
    public void updateKhuyenMaiProducts(Integer khuyenMaiId, List<Integer> productIds) {
        // Lấy khuyến mãi hiện tại
        KhuyenMai khuyenMai = khuyenMaiRepository.findById(khuyenMaiId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khuyến mãi với ID: " + khuyenMaiId));
        // Loại bỏ tất cả sản phẩm hiện tại liên kết với khuyến mãi
        List<SanPhamChiTiet> existingProducts = sanPhamChiTietRepository.findAllByKhuyenMaiId(khuyenMaiId);
        for (SanPhamChiTiet spct : existingProducts) {
            spct.setKhuyenMai(null);
        }
        sanPhamChiTietRepository.saveAll(existingProducts);
        // Liên kết các sản phẩm mới
        List<SanPhamChiTiet> newProducts = sanPhamChiTietRepository.findAllById(productIds);
        for (SanPhamChiTiet spct : newProducts) {
            spct.setKhuyenMai(khuyenMai);
        }
        sanPhamChiTietRepository.saveAll(newProducts);
    }
    private void checkAndUpdateStatus(KhuyenMai khuyenMai) {
        if (khuyenMai.getNgayKetThuc() != null && khuyenMai.getNgayKetThuc().isBefore(LocalDate.now())) {
            if (khuyenMai.getTrangThai() != 0) { // Tránh cập nhật không cần thiết
                khuyenMai.setTrangThai(0); // 0 = Không hoạt động
                khuyenMaiRepository.save(khuyenMai); // Lưu thay đổi vào database
            }
        }
    }
}
