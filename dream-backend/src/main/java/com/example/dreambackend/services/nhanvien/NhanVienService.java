package com.example.dreambackend.services.nhanvien;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.entities.VaiTro;
import com.example.dreambackend.repositories.NhanVienRepository;
import com.example.dreambackend.repositories.VaiTroRepository;
import com.example.dreambackend.requests.NhanVienRequest;
import com.example.dreambackend.responses.NhanVienResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

//    @Autowired
//    private PasswordEncoder passwordEncoder;  // Inject PasswordEncoder vào Service

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

    // Phương thức kiểm tra đăng nhập
    @Override
    public NhanVien login(String email, String password) {
        // Kiểm tra xem nhân viên có tồn tại không
        Optional<NhanVien> nhanVienOptional = nhanVienRepository.findByEmail(email);

        if (nhanVienOptional.isPresent()) {
            NhanVien nhanVien = nhanVienOptional.get();

//            // Kiểm tra mật khẩu
//            if (passwordEncoder.matches(password, nhanVien.getMatKhau())) {
//                // Đăng nhập thành công, trả về thông tin nhân viên
//                return nhanVien; // Trả về thông tin nhân viên nếu đăng nhập thành công
                if (password.equals(nhanVien.getMatKhau())) {
                    // Đăng nhập thành công, trả về thông tin nhân viên
                    return nhanVien; // Trả về thông tin nhân viên nếu đăng nhập thành công
            } else {
                // Mật khẩu không đúng
                throw new RuntimeException("Sai mật khẩu.");
            }
        } else {
            // Không tìm thấy nhân viên với email này
            throw new RuntimeException("Không tìm thấy nhân viên với email: " + email);
        }
    }





}
