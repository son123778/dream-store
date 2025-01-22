//package com.example.dreambackend.request;
//
//import jakarta.validation.constraints.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//public class NhanVienRequest {
//
//    @NotNull(message = "Vai trò không được để trống")
//    private Integer idVaiTro;
//
//    @NotBlank(message = "Mã nhân viên không được để trống")
//    @Size(max = 10, message = "Mã nhân viên tối đa 10 ký tự")
//    private String ma;
//
//    @NotBlank(message = "Tên không được để trống")
//    @Size(max = 50, message = "Tên tối đa 50 ký tự")
//    private String ten;
//
//    private Boolean gioiTinh;
//
//    @NotNull(message = "Ngày sinh không được để trống")
//    private String ngaySinh;
//
//    @Email(message = "Email không hợp lệ")
//    private String email;
//
//    @Size(max = 15, message = "Số điện thoại tối đa 15 ký tự")
//    private String soDienThoai;
//
//    private String taiKhoan;
//    private String matKhau;
//    private Integer trangThai;
//}
