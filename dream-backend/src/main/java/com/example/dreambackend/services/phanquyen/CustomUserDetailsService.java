package com.example.dreambackend.services.phanquyen;

import com.example.dreambackend.entities.NhanVien;
import com.example.dreambackend.repositories.NhanVienRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private NhanVienRepository nhanVienRepository;  // Dịch vụ nhân viên để lấy thông tin người dùng



    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch the NhanVien using Optional and handle not found scenario
        Optional<NhanVien> optionalNhanVien = nhanVienRepository.findByEmail(email);

        // If the employee is not found, throw UsernameNotFoundException
        NhanVien nhanVien = optionalNhanVien
                .orElseThrow(() -> new UsernameNotFoundException("Nhân viên không tồn tại với email: " + email));
//        String role = "ROLE_" + nhanVien.getVaiTro().getTen(); // Vai trò trong VaiTro đã có tên như "ADMIN", "USER", v.v.
        // Convert NhanVien to UserDetails with roles and password
        return User.builder()
                .username(nhanVien.getEmail())
                .password(nhanVien.getMatKhau())  // Assuming the password is already encoded
                .roles(nhanVien.getVaiTro().getTen())  // Get the role name from VaiTro
                .build();
    }



}
