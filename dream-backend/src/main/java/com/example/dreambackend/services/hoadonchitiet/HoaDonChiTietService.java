package com.example.dreambackend.services.hoadonchitiet;

import com.example.dreambackend.entities.HoaDon;
import com.example.dreambackend.entities.HoaDonChiTiet;
import com.example.dreambackend.entities.SanPhamChiTiet;
import com.example.dreambackend.repositories.HoaDonChiTietRepository;
import com.example.dreambackend.repositories.HoaDonRepository;
import com.example.dreambackend.repositories.SanPhamChiTietRepository;
import com.example.dreambackend.requests.HoaDonChiTietRequest;
import com.example.dreambackend.requests.HoaDonChiTietSearchRequest;
import com.example.dreambackend.responses.HoaDonChiTietResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HoaDonChiTietService implements IHoaDonChiTietService {

    @PersistenceContext
    private EntityManager em;
    private final HoaDonChiTietRepository hdctRepository;
    private final HoaDonRepository hoaDonRepository;
    private final SanPhamChiTietRepository spctRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public HoaDonChiTietResponse addSanPhamToHoaDon(Integer hoaDonId, Integer sanPhamChiTietId, Integer soLuong) {
        HoaDon hoaDon = hoaDonRepository.findById(hoaDonId)
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
        SanPhamChiTiet spct = spctRepository.findById(sanPhamChiTietId)
                .orElseThrow(() -> new RuntimeException("Sản phẩm chi tiết không tồn tại"));

        if (spct.getSoLuong() < soLuong) {
            throw new RuntimeException("Số lượng vượt quá tồn kho");
        }

        Optional<HoaDonChiTiet> existingHdct = hdctRepository.findByHoaDonAndSanPhamChiTiet(hoaDon, spct);

        HoaDonChiTiet savedHdct;
        if (existingHdct.isPresent()) {
            HoaDonChiTiet hdct = existingHdct.get();
            hdct.setSoLuong(hdct.getSoLuong() + soLuong);
            hdct.setNgaySua(LocalDate.now());
            savedHdct = hdctRepository.save(hdct);
        } else {
            HoaDonChiTiet newHdct = HoaDonChiTiet.builder()
                    .hoaDon(hoaDon)
                    .sanPhamChiTiet(spct)
                    .soLuong(soLuong)
                    .donGia(Optional.ofNullable(spct.getGia()).orElse(0.0))
                    .ngayTao(LocalDate.now())
                    .trangThai(1)
                    .build();
            savedHdct = hdctRepository.save(newHdct);
        }

        spct.setSoLuong(spct.getSoLuong() - soLuong);
        spctRepository.save(spct);

        return convertToDTO(savedHdct);
    }

    @Override
    public HoaDonChiTietResponse updateHoaDonChiTiet(Integer id, Integer soLuong) {
        HoaDonChiTiet hdct = hdctRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chi tiết hóa đơn không tồn tại"));

        SanPhamChiTiet spct = hdct.getSanPhamChiTiet();
        int soLuongThayDoi = soLuong - hdct.getSoLuong();

        if (soLuongThayDoi > 0) {
            if (spct.getSoLuong() < soLuongThayDoi) {
                throw new RuntimeException("Số lượng vượt quá tồn kho");
            }
            spct.setSoLuong(spct.getSoLuong() - soLuongThayDoi);
        } else if (soLuongThayDoi < 0) {
            spct.setSoLuong(spct.getSoLuong() - soLuongThayDoi);
        }

        spctRepository.save(spct);

        hdct.setSoLuong(soLuong);
        hdct.setNgaySua(LocalDate.now());

        return convertToDTO(hdctRepository.save(hdct));
    }

    @Override
    public List<HoaDonChiTietResponse> search(HoaDonChiTietSearchRequest searchRequest) {
        return hdctRepository.search(searchRequest,em);
    }

    private LocalDate parseDate(String dateString) {
        return (dateString != null && !dateString.isEmpty()) ? LocalDate.parse(dateString, DATE_FORMATTER) : LocalDate.now();
    }

    private HoaDonChiTiet convertToEntity(HoaDonChiTietRequest request) {
        HoaDon hoaDon = hoaDonRepository.findById(request.getIdHoaDon())
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
        SanPhamChiTiet spct = spctRepository.findById(request.getIdSanPhamChiTiet())
                .orElseThrow(() -> new RuntimeException("Sản phẩm chi tiết không tồn tại"));

        return HoaDonChiTiet.builder()
                .hoaDon(hoaDon)
                .sanPhamChiTiet(spct)
                .ma(request.getMa())
                .soLuong(request.getSoLuong())
                .donGia(Optional.ofNullable(request.getDonGia()).orElse(0.0))
                .ngayTao(request.getNgayTao())
                .ngaySua(request.getNgaySua())
                .trangThai(request.getTrangThai())
                .build();
    }

    private HoaDonChiTietResponse convertToDTO(HoaDonChiTiet hdct) {
        return HoaDonChiTietResponse.builder()
                .idHoaDon(hdct.getHoaDon().getId())
                .idSanPhamChiTiet(hdct.getSanPhamChiTiet().getId())
                .maHoaDonChiTiet(hdct.getMa())
                .soLuong(hdct.getSoLuong())
                .donGia(hdct.getDonGia())
                .ngayTao(hdct.getNgayTao())
                .ngaySua(hdct.getNgaySua())
                .trangThai(hdct.getTrangThai())
                .build();
    }
}
