package com.example.dreambackend.services.nhanvien;

import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.entities.VaiTro;
import com.example.dreambackend.repositories.NhanVienRepository;
import com.example.dreambackend.repositories.VaiTroRepository;
import com.example.dreambackend.requests.NhanVienRequest;
import com.example.dreambackend.respones.NhanVienResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NhanVienService implements INhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Override
    public List<NhanVienResponse> getAllNhanVien() {
        return nhanVienRepository.getAllNhanVienResponses();
    }

    @Override
    public List<NhanVienResponse> searchNhanVienByName(String ten) {
        return nhanVienRepository.searchNhanVienByName(ten);
    }

    @Override
    public List<NhanVienResponse> filterNhanVienByTrangThai(Integer trangThai) {
        return nhanVienRepository.filterNhanVienByTrangThai(trangThai);
    }

    @Override
    public NhanVien addNhanVien(NhanVienRequest nhanVienRequest) {
        NhanVien nhanVien = new NhanVien();
        BeanUtils.copyProperties(nhanVienRequest, nhanVien);

        VaiTro vaiTro = vaiTroRepository.findById(nhanVienRequest.getIdVaiTro())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vai trò với id: " + nhanVienRequest.getIdVaiTro()));
        nhanVien.setVaiTro(vaiTro);

        // Nếu frontend gửi ngayTao, sử dụng nó, nếu không thì lấy LocalDateTime.now()
        nhanVien.setNgayTao(nhanVienRequest.getNgayTao() != null ? nhanVienRequest.getNgayTao() : LocalDateTime.now());
        nhanVien.setNgaySua(nhanVien.getNgayTao()); // Khi thêm mới, ngaySua = ngayTao

        return nhanVienRepository.save(nhanVien);
    }

    @Override
    public NhanVien updateNhanVien(NhanVienRequest nhanVienRequest) {
        NhanVien nhanVien = nhanVienRepository.findById(nhanVienRequest.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với id: " + nhanVienRequest.getId()));

        VaiTro vaiTro = vaiTroRepository.findById(nhanVienRequest.getIdVaiTro())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vai trò với id: " + nhanVienRequest.getIdVaiTro()));

        // Lưu ngày tạo cũ
        LocalDateTime ngayTaoGoc = nhanVien.getNgayTao();

        BeanUtils.copyProperties(nhanVienRequest, nhanVien, "id", "ngayTao");
        nhanVien.setVaiTro(vaiTro);
        nhanVien.setNgayTao(ngayTaoGoc); // Giữ nguyên ngày tạo
        nhanVien.setNgaySua(LocalDateTime.now()); // Cập nhật ngày sửa

        return nhanVienRepository.save(nhanVien);
    }
}
