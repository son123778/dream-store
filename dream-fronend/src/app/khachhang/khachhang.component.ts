import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KhachHangService } from './khachhang.service';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-khachhang',
  imports: [CommonModule, FormsModule],
  templateUrl: './khachhang.component.html',
  styleUrl: './khachhang.component.css',
})
export class KhachhangComponent implements OnInit {
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
  erroredits: any = {};
  khachhang: any = {
    id: '',
    ma: '',
    ten: '',
    gioiTinh: true,
    email: '',
    soDienThoai: '',
    matKhau: '',
    ngayTao: '',
    ngaySua: '',
    trangThai: 1
  };

  constructor(private khachHangService: KhachHangService) { }
  ngOnInit(): void {
    this.loadData()

  }

  resetForm() {
    this.khachhang = {
    ma: '',
    ten: '',
    gioiTinh: true,
    email: '',
    soDienThoai: '',
    matKhau: '',
    ngayTao: '',
    ngaySua: '',
    trangThai: 1,
    };

  }
  editKhachHang(khachHangId: number) {
    this.khachHangEdit = this.khachhangs.find(khachhang => khachhang.id === khachHangId);
    this.showModalEdit = true;
    
  }

  showDetail(khachHangId: number) {
    this.selectedKhachHang = this.khachhangs.find(khachhang => khachhang.id === khachHangId);
    this.showModalDetail = true;
  }

  addKhachHang() {
    if (!this.validateForm()) {
      return;
    }
    this.khachHangService.addKhachHang(this.khachhang).subscribe(
      (response) => {
        alert('Thêm khách hàng thành công!');
        this.loadData();
        
        this.closeModal();
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
  clearErrorEdit(field: string): void {
    if (this.erroredits[field]) {
      delete this.erroredits[field];
    }
  }

  validateForm(): boolean {
    this.errors = {};

    if (!this.khachhang.ma.trim()) {
      this.errors.ma = 'Mã khách hàng không được để trống!';
    }
    // else {
    //   const isDuplicate = this.khachhangs.some(khachhang => khachhang.ma == this.khachhang.ma);
    //   if (isDuplicate) {
    //     this.errors.ma = 'Mã khách hàng đã tồn tại!';
    //   }
    // }

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
  if (!this.khachhang.email.trim()) {
    this.errors.email = 'Email không được để trống!';
  }else{
    this.khachHangService.getKhachHangByEmail(this.khachhang.email).subscribe(
      (data) => {
        // Kiểm tra dữ liệu trả về
        if (data===null) {
          this.errors.email = 'Email không tồn tại!';
        } 
      });
  }
    if (!this.khachhang.matKhau.trim()) {
      this.errors.matKhau = 'Mật khẩu khách hàng không được để trống!';
    }

    
    if (this.khachhang.trangThai === null || this.khachhang.trangThai === undefined) {
      this.errors.trangThai = 'Vui lòng chọn trạng thái!';
    }

    return Object.keys(this.errors).length === 0;
  }

  searchAndShowSearch(): void {
    if (this.searchText.trim() === '') {
      alert('Vui lòng nhập tên khách hàng để tìm kiếm.');
      return;
    }

    
    this.khachHangService.searchKhachHangByName(this.searchText).subscribe(
      (data) => {
        if (data.length > 0) {
          this.selectedKhachHang = data[0]; 
          this.showModalSearch = true; // Mở modal chi tiết
        } else {
          alert('Không tìm thấy khách hàng phù hợp.');
        }
      },
      (error) => {
        console.error('Lỗi khi tìm kiếm khách hàng:', error);
        alert('Đã xảy ra lỗi khi tìm kiếm.');
      }
    );
  }
  validateEditForm(): boolean {

    this.errors = {};

    if (!this.khachHangEdit.ma.trim()) {
      this.errors.ma = 'Mã khách hàng không được để trống!';
    }
    // else {
    //   const isDuplicate = this.khachhangs.some(khachhang => khachhang.ma == this.khachhang.ma);
    //   if (isDuplicate) {
    //     this.errors.ma = 'Mã khách hàng đã tồn tại!';
    //   }
    // }

    if (!this.khachHangEdit.ten.trim()) {
      this.errors.ten = 'Tên khách hàng không được để trống!';
    }
      // Validate số điện thoại
  const phoneRegex = /^(\+?\d{1,3}[- ]?)?\(?\d{3}\)?[- ]?\d{3}[- ]?\d{4}$/;
  if (!this.khachHangEdit.soDienThoai.trim()) {
    this.errors.soDienThoai = 'Số điện thoại khách hàng không được để trống!';
  } else if (!phoneRegex.test(this.khachHangEdit.soDienThoai)) {
    this.errors.soDienThoai = 'Số điện thoại không hợp lệ!';
  }
  if (!this.khachhang.email.trim()) {
    this.errors.email = 'Email không được để trống!';
  }else{
    this.khachHangService.getKhachHangByEmail(this.khachhang.email).subscribe(
      (data) => {
        // Kiểm tra dữ liệu trả về
        if (data===null) {
          this.errors.email = 'Email không tồn tại!';
        } 
      });
  }
    if (!this.khachHangEdit.matKhau.trim()) {
      this.errors.matKhau = 'Mật khẩu khách hàng không được để trống!';
    }

    
    if (this.khachHangEdit.trangThai === null || this.khachHangEdit.trangThai === undefined) {
      this.errors.trangThai = 'Vui lòng chọn trạng thái!';
    }

    return Object.keys(this.errors).length === 0;
  }

  updateKhachHang() {
    if (!this.validateEditForm()) {
      return;
    }
    if (this.khachHangEdit!=null) {
      this.khachHangService.updateKhachHang(this.khachHangEdit).subscribe(
        (response) => {


          alert('Cập nhật khách hàng thành công!');
          this.loadData();
          console.log('Updated khách hàng:', this.khachhang.ngaySua);
          this.closeModalEdit();

        },
        (error) => {
          console.error('Error:', error);
          alert('Có lỗi xảy ra khi cập nhật khách hàng.');
        }
      );
    } else {
      alert('ID khách hàng không hợp lệ!');
    }
  }

  
  
  loadData(): void {
    this.loadPage(0)
  }

  getKhachHangDetail(id: number): void {
    this.khachHangService.getKhachHangDetail(id).subscribe(
      (data) => {
        this.khachhang = data;

      },
      (error) => {
        console.error('Lỗi khi lấy chi tiết khách hàng:', error);
        alert('Không tìm thấy thông tin khách hàng!');
      }
    );
  }

  loadPage(page: number): void {
    this.khachHangService.getKhachHang(page, 8).subscribe((response) => {
      this.khachhangs = response.content; // Dữ liệu của trang hiện tại
      this.totalPages = response.totalPages; // Tổng số trang
      this.currentPage = page; // Cập nhật trang hiện tại
      this.updateVisiblePages();
      this.filterKhachHangs();
    });
  }
  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.loadPage(page); // Load the selected page
    } else {
      console.warn('Invalid page number:', page);
    }
  }
  filterKhachHangs() {
    if (this.searchText.trim()) {
      this.filteredKhachHangs = this.khachhangs.filter((khachhang) =>
        khachhang.ten.toLowerCase().includes(this.searchText.toLowerCase())
      );
    } else {
      this.filteredKhachHangs = [...this.khachhangs]; // Hiển thị tất cả nếu không tìm kiếm
    }
  }
  goToPreviousPage(): void {
    if (this.currentPage > 0) {
      this.loadPage(this.currentPage - 1);
    }
  }
  updateVisiblePages(): void {
    const startPage = Math.floor(this.currentPage / this.maxVisiblePages) * this.maxVisiblePages;
    const endPage = Math.min(startPage + this.maxVisiblePages, this.totalPages);

    this.visiblePages = Array.from({ length: endPage - startPage }, (_, i) => startPage + i);
  }

  goToNextPage(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.loadPage(this.currentPage + 1);
    }
  }

  openModal() {
    this.resetForm();
    this.showModal = true;
  }

  // Hàm đóng modal
  closeModal() {
    this.errors = {}
    this.resetForm();
    this.showModal = false;
  }
  openModalDetail() {
    this.resetForm();
    this.showModalDetail = true;
  }
  closeModalDetail() {
    this.resetForm();
    this.showModalDetail = false;
  }
  openModalEdit() {
    this.resetForm();
    this.showModalEdit = true;
  }
  closeModalEdit() {
    this.errors = {}
    this.resetForm();
    this.showModalEdit = false;
  }
  openModalSearch() {
    this.resetForm();
    this.showModalSearch = true;
  }
  closeModalSearch() {
    this.resetForm();
    this.showModalSearch = false;
  }

}