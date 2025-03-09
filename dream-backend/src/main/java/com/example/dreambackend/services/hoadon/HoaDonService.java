package com.example.dreambackend.services.hoadon;

import com.example.dreambackend.entities.*;
import com.example.dreambackend.repositories.*;
import com.example.dreambackend.requests.HoaDonRequest;
import com.example.dreambackend.requests.HoaDonSearchRequest;
import com.example.dreambackend.responses.HoaDonResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HoaDonService implements IHoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private PhuongThucThanhToanRepository ptttRepository;
    @PersistenceContext
    private EntityManager em;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public HoaDonResponse updateHoaDon(Integer id, HoaDonRequest request) {
        HoaDon hoaDon = hoaDonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));

        hoaDon.setTenNguoiNhan(request.getTenNguoiNhan());
        if (request.getIdPhuongThucThanhToan() != null) {
            PhuongThucThanhToan phuongThucThanhToan = ptttRepository.findById(request.getIdPhuongThucThanhToan())
                    .orElseThrow(() -> new RuntimeException("Phương thức thanh toán không tồn tại"));
            hoaDon.setPhuongThucThanhToan(phuongThucThanhToan);
        }

        hoaDon.setSdtNguoiNhan(request.getSdtNguoiNhan());
        hoaDon.setDiaChiNhanHang(request.getDiaChiNhanHang());
        hoaDon.setPhiVanChuyen(request.getPhiVanChuyen());
        hoaDon.setGhiChu(request.getGhiChu());
        hoaDon.setHinhThucThanhToan(request.getHinhThucThanhToan());
        hoaDon.setTongTienThanhToan(request.getTongTienThanhToan());
        hoaDon.setTongTienTruocVoucher(request.getTongTienTruocVoucher());
        hoaDon.setTrangThai(request.getTrangThai());

        hoaDon.setNgaySua(LocalDate.now());

        if (request.getIdVoucher() != null) {
            Voucher voucher = voucherRepository.findById(request.getIdVoucher())
                    .orElseThrow(() -> new RuntimeException("Voucher không tồn tại"));
            hoaDon.setVoucher(voucher);
        }

        return convertToDTO(hoaDonRepository.save(hoaDon));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public HoaDonResponse createHoaDon(HoaDonRequest request) {
        HoaDon hoaDon = convertToEntity(request);
        return convertToDTO(hoaDonRepository.save(hoaDon));
    }

    @Override
    public HoaDonResponse findById(Integer id) {
        HoaDon hoaDon = hoaDonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));
        return convertToDTO(hoaDon);
    }

    @Override
    public List<HoaDonResponse> getAllHoaDon(HoaDonSearchRequest request) {
        return hoaDonRepository.search(request, em);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void cancelHoaDon(Integer id) {
        HoaDon hoaDon = hoaDonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hoá đơn không tồn tại"));

        if (hoaDon.getTrangThai() != 1) {
            throw new RuntimeException("Không thể huỷ hoá đơn trong trang thái chờ");
        }

        hoaDon.setTrangThai(4);
        hoaDonRepository.save(hoaDon);

        List<HoaDonChiTiet> list = hoaDonChiTietRepository.findByHoaDonId(id);
        List<SanPhamChiTiet> listSPCT = new ArrayList<>();
        for (HoaDonChiTiet hdct : list) {
            listSPCT.add(hdct.getSanPhamChiTiet());
        }
        for (SanPhamChiTiet sanPhamChiTiet : listSPCT) {
            SanPhamChiTiet spct = sanPhamChiTietRepository.findById(sanPhamChiTiet.getId())
                    .orElseThrow(() -> new RuntimeException("Sản phầm chi tiết không tồn tại"));
            sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() + spct.getSoLuong());
            sanPhamChiTietRepository.save(sanPhamChiTiet);
        }
    }

    private HoaDon convertToEntity(HoaDonRequest request) {
        KhachHang khachHang = khachHangRepository.findById(request.getIdKhachHang())
                .orElseThrow(() -> new RuntimeException("Khách hàng không tồn tại"));
        NhanVien nhanVien = nhanVienRepository.findById(request.getIdNhanVien())
                .orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại"));
        PhuongThucThanhToan pttt = ptttRepository.findById(request.getIdPhuongThucThanhToan())
                .orElseThrow(() -> new RuntimeException("Phương thức thanh toán không tồn tại"));

        Voucher voucher = null;
        if (request.getIdVoucher() != null) {
            voucher = voucherRepository.findById(request.getIdVoucher())
                    .orElseThrow(() -> new RuntimeException("Voucher không tồn tại"));
        }

        return HoaDon.builder()
                .ma(generateMaHoaDon())
                .khachHang(khachHang)
                .nhanVien(nhanVien)
                .phuongThucThanhToan(pttt)
                .voucher(voucher)
                .tenNguoiNhan(request.getTenNguoiNhan())
                .sdtNguoiNhan(request.getSdtNguoiNhan())
                .diaChiNhanHang(request.getDiaChiNhanHang())
                .hinhThucThanhToan(request.getHinhThucThanhToan())
                .phiVanChuyen(request.getPhiVanChuyen())
                .tongTienTruocVoucher(request.getTongTienTruocVoucher())
                .tongTienThanhToan(request.getTongTienThanhToan())
                .ngayNhanDuKien(parseDate(request.getNgayNhanDuKien()))
                .ngayTao(parseDate(request.getNgayTao()))
                .ngaySua(parseDate(request.getNgaySua()))
                .trangThai(request.getTrangThai() != null ? request.getTrangThai() : 1)
                .ghiChu(request.getGhiChu())
                .build();
    }

    private HoaDonResponse convertToDTO(HoaDon hoaDon) {
        return HoaDonResponse.builder()
                .id(hoaDon.getId())
                .idKhachHang(hoaDon.getKhachHang().getId())
                .idNhanVien(hoaDon.getNhanVien().getId())
                .idVoucher(hoaDon.getVoucher() != null ? hoaDon.getVoucher().getId() : null)
                .idPhuongThucThanhToan(hoaDon.getPhuongThucThanhToan().getId())
                .tenNguoiNhan(hoaDon.getTenNguoiNhan())
                .sdtNguoiNhan(hoaDon.getSdtNguoiNhan())
                .diaChiNhanHang(hoaDon.getDiaChiNhanHang())
                .hinhThucThanhToan(hoaDon.getHinhThucThanhToan())
                .phiVanChuyen(hoaDon.getPhiVanChuyen())
                .tongTienTruocVoucher(hoaDon.getTongTienTruocVoucher())
                .tongTienThanhToan(hoaDon.getTongTienThanhToan())
                .ngayNhanDuKien(hoaDon.getNgayNhanDuKien())
                .ngayTao(hoaDon.getNgayTao())
                .ngaySua(hoaDon.getNgaySua())
                .trangThai(hoaDon.getTrangThai())
                .ghiChu(hoaDon.getGhiChu())
                .build();
    }

    private LocalDate parseDate(String dateString) {
        return (dateString != null && !dateString.isEmpty()) ? LocalDate.parse(dateString, DATE_FORMATTER) : LocalDate.now();
    }

    private String generateMaHoaDon() {
        return "HD" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
