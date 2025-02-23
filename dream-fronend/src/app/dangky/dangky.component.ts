import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DangKyService } from './dangky.service';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-dangky',
  imports: [CommonModule, FormsModule],
  templateUrl: './dangky.component.html',
  styleUrl: './dangky.component.css',
})
export class DangkyComponent implements OnInit {
  xacNhanMatKhau: string = '';
  khachhangs: any[] = [];
  showModal: boolean = false;
  showModalDetail: boolean = false;
  showModalSearch: boolean = false;
  maxVisiblePages = 3;
  totalPages: number = 0;
  currentPage: number = 0;
  pageSize: number = 10;
  showModalEdit = false;
  selectedKhachHang: any = null;
  khachHangEdit: any = {};
  searchText: string = '';
  visiblePages: number[] = [];
  filteredKhachHangs: any[] = [];
  errors: any = {};
  khachhang: any = {
    id: '',
    ma: '',
    ten: '',
    gioiTinh: true,
    soDienThoai: '',
    matKhau: '',
    ngayTao: '',
    ngaySua: '',
    trangThai: null
  };

  constructor(private dangKyService: DangKyService) { }
  ngOnInit(): void {
    
  }

  resetForm() {
    this.khachhang = {
    ma: '',
    ten: '',
    gioiTinh: null,
    soDienThoai: '',
    matKhau: '',
    ngayTao: '',
    ngaySua: '',
    trangThai: null,
    };

  }
  
  addKhachHang() {
    if (!this.validateForm()) {
      return; // Stop execution if form is invalid
    }
    this.dangKyService.addKhachHang(this.khachhang).subscribe(
      (response) => {
        alert('Thêm khách hàng thành công!');
        
        this.resetForm();
      },
      (error) => {
        console.error('Error:', error);
        alert('Có lỗi xảy ra khi thêm khách hàng.');
      }
    );
  }

  clearError(field: string): void {
    if (this.errors[field]) {
      delete this.errors[field];
    }
  }

  validateForm(): boolean {
    this.errors = {};

    if (!this.khachhang.ten.trim()) {
      this.errors.ten = 'Tên khách hàng không được để trống!';
    }
      // Validate số điện thoại
  const phoneRegex = /^(\+?\d{1,3}[- ]?)?\(?\d{3}\)?[- ]?\d{3}[- ]?\d{4}$/;
  if (!this.khachhang.soDienThoai.trim()) {
    this.errors.soDienThoai = 'Số điện thoại khách hàng không được để trống!';
  } else if (!phoneRegex.test(this.khachhang.soDienThoai)) {
    this.errors.soDienThoai = 'Số điện thoại không hợp lệ!';
  }
    if (!this.khachhang.matKhau.trim()) {
      this.errors.matKhau = 'Mật khẩu khách hàng không được để trống!';
    }
    if (!this.xacNhanMatKhau.trim()) {
      this.errors.xacNhanMatKhau = 'Xác nhận mật khẩu không được để trống!';
    }
    if (this.xacNhanMatKhau!=this.khachhang.matKhau) {
      this.errors.xacNhanMatKhau = 'Xác nhận mật khẩu không đúng!';
    }

    
    
    return Object.keys(this.errors).length === 0;
  }

  
}