package com.example.dreambackend.services.nhanvien;

import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.requests.NhanVienRequest;
import com.example.dreambackend.responses.NhanVienResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface INhanVienService {
    List<NhanVienResponse> getAllNhanVien();
    List<NhanVienResponse> searchNhanVienByName(String ten);
    List<NhanVienResponse> filterNhanVienByTrangThai(Integer trangThai);
    NhanVien addNhanVien(NhanVienRequest nhanVienRequest);
    NhanVien updateNhanVien(NhanVienRequest nhanVienRequest);
    // Phương thức đăng nhập (trả về boolean)
    ResponseEntity<?> login(String email, String password);

}
