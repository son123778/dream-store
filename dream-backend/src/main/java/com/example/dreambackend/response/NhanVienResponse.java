package com.example.dreambackend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NhanVienResponse {
    private Integer id;               // ID nhân viên
    private String ma;                // Mã nhân viên
    private String ten;               // Tên nhân viên
    private Boolean gioiTinh;         // Giới tính (true: Nam, false: Nữ)
    private LocalDate ngaySinh;       // Ngày sinh
    private String email;             // Email
    private String soDienThoai;       // Số điện thoại
    private String taiKhoan;          // Tài khoản đăng nhập
    private String matKhau;           // Mật khẩu
    private String anh;               // Đường dẫn ảnh đại diện
    private LocalDate ngayTao;    // Ngày tạo bản ghi
    private LocalDate ngaySua;    // Ngày sửa bản ghi
    private Integer trangThai;        // Trạng thái (Active/Inactive)
    private String tenVaiTro;         // Tên vai trò (lấy từ bảng VaiTro)

}
