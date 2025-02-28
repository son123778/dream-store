package com.example.dreambackend.services.nhanvien;

import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.entities.VaiTro;
import com.example.dreambackend.repositories.NhanVienRepository;
import com.example.dreambackend.repositories.VaiTroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
public class NhanVienService implements INhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Transactional
    @Override
    public Page<NhanVien> getAllNhanVienPaged(int page, int size) {
        return nhanVienRepository.findAll(PageRequest.of(page, size));
    }



    @Transactional
    @Override
    public NhanVien addNhanVien(NhanVien nhanVien) {
        VaiTro vaiTro = vaiTroRepository.findById(nhanVien.getVaiTro().getId())
                .orElseThrow(() -> new IllegalArgumentException("Vai trò không tồn tại!"));

        // Gán vai trò cho nhân viên
        nhanVien.setVaiTro(vaiTro);
        // Gán ngày tạo hiện tại
        nhanVien.setNgayTao(LocalDate.now());

        return nhanVienRepository.save(nhanVien);
    }

    // Đường dẫn thư mục lưu trữ ảnh
    private static final String UPLOAD_DIR = "uploads/images/";

    // Cập nhật hoặc thêm ảnh cho nhân viên
    @Transactional
    @Override
    public NhanVien addImageForNhanVien(Integer nhanVienId, MultipartFile file) throws IOException {
        NhanVien existingNhanVien = nhanVienRepository.findById(nhanVienId)
                .orElseThrow(() -> new IllegalArgumentException("Nhân viên không tồn tại!"));

        if (file != null && !file.isEmpty()) {
            // Lấy tên file ảnh
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);

            // Tạo thư mục nếu chưa tồn tại
            Files.createDirectories(path.getParent());

            // Lưu ảnh vào thư mục
            Files.write(path, file.getBytes());

            existingNhanVien.setAnh(fileName); // Cập nhật đường dẫn ảnh
        }

        return nhanVienRepository.save(existingNhanVien);
    }

    @Override
    @Transactional
    public NhanVien updateNhanVien(NhanVien nhanVien) {
        // 🔹 Kiểm tra nhân viên có tồn tại không
        NhanVien existingNhanVien = nhanVienRepository.findById(nhanVien.getId())
                .orElseThrow(() -> new IllegalArgumentException("Nhân viên không tồn tại!"));

        // 🔹 Kiểm tra vai trò có tồn tại không
        VaiTro vaiTro = vaiTroRepository.findById(nhanVien.getVaiTro().getId())
                .orElseThrow(() -> new IllegalArgumentException("Vai trò không tồn tại!"));

        // 🔹 Cập nhật thông tin nhân viên
        existingNhanVien.setTen(nhanVien.getTen());
        existingNhanVien.setGioiTinh(nhanVien.getGioiTinh());
        existingNhanVien.setNgaySinh(nhanVien.getNgaySinh());
        existingNhanVien.setEmail(nhanVien.getEmail());
        existingNhanVien.setSoDienThoai(nhanVien.getSoDienThoai());
        existingNhanVien.setTaiKhoan(nhanVien.getTaiKhoan());
        existingNhanVien.setMatKhau(nhanVien.getMatKhau());
        existingNhanVien.setTrangThai(nhanVien.getTrangThai());
        existingNhanVien.setNgaySua(LocalDate.now());

        // 🔹 Gán vai trò mới
        existingNhanVien.setVaiTro(vaiTro);

        return nhanVienRepository.save(existingNhanVien);
    }


    @Override
    public NhanVien getNhanVienById(Integer id) {
        return nhanVienRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nhân viên không tồn tại với id: " + id));
    }

    @Override
    public List<NhanVien> searchNhanVienByName(String ten) {
        return nhanVienRepository.findByTenContainingIgnoreCase(ten);
    }
}
