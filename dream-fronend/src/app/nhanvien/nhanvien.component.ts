import { Component, OnInit } from '@angular/core';
import { NhanVienService, NhanVien, NhanVienRequest, VaiTro } from './nhanvien.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-nhanvien',
  imports: [CommonModule, FormsModule],
  templateUrl: './nhanvien.component.html',
  styleUrls: ['./nhanvien.component.css']
})
export class NhanvienComponent implements OnInit {
  nhanViens: NhanVien[] = [];
  vaiTros: VaiTro[] = [];
  state: 'list' | 'form' = 'list';
  isEditing: boolean = false;
  currentNhanVien: NhanVienRequest = this.initializeNhanVien();
  searchTen: string = '';
  filterTrangThai: number | '' = '';

  constructor(private nhanVienService: NhanVienService) {}

  ngOnInit(): void {
    this.loadNhanViens();
    this.loadVaiTros();
  }

  loadNhanViens(): void {
    this.nhanVienService.getNhanVien().subscribe((data) => {
      this.nhanViens = data;
    });
  }

  loadVaiTros(): void {
    this.nhanVienService.getVaiTro().subscribe((data) => {
      this.vaiTros = data;
    });
  }

  showCreateForm(): void {
    this.state = 'form';
    this.isEditing = false;
    this.currentNhanVien = this.initializeNhanVien();
  }

  showEditForm(nhanVien: NhanVien): void {
    this.state = 'form';
    this.isEditing = true;
    this.currentNhanVien = { ...nhanVien };

    // Cập nhật ngày sửa thành ngày hiện tại (định dạng LocalDateTime)
    this.currentNhanVien.ngaySua = this.getCurrentLocalDateTime();
  }

  backToList(): void {
    this.state = 'list';
    this.loadNhanViens();
  }

  saveNhanVien(): void {
    if (this.isEditing) {
      this.currentNhanVien.ngaySua = this.getCurrentLocalDateTime();

      this.nhanVienService.updateNhanVien(this.currentNhanVien).subscribe({
        next: () => {
          alert('Cập nhật nhân viên thành công');
          this.backToList();
        },
        error: (err) => {
          console.error('Lỗi khi cập nhật nhân viên:', err);
          alert('Cập nhật nhân viên thất bại');
        }
      });
    } else {
      this.currentNhanVien.ngayTao = this.getCurrentLocalDateTime();
      this.currentNhanVien.ngaySua = null; // Khi thêm mới, ngày sửa để null

      this.nhanVienService.addNhanVien(this.currentNhanVien).subscribe({
        next: () => {
          alert('Thêm nhân viên thành công');
          this.backToList();
        },
        error: (err) => {
          console.error('Lỗi khi thêm nhân viên:', err);
          alert('Thêm nhân viên thất bại');
        }
      });
    }
  }

  searchNhanVien(): void {
    if (!this.searchTen.trim()) {
      this.loadNhanViens();
      return;
    }

    this.nhanVienService.searchNhanVienByName(this.searchTen).subscribe({
      next: (data) => {
        this.nhanViens = data;
      },
      error: (err) => {
        console.error('Lỗi khi tìm kiếm nhân viên:', err);
        alert('Không thể tìm kiếm nhân viên!');
      }
    });
  }

  filterNhanVien(): void {
  if (this.filterTrangThai === '') {
    this.loadNhanViens(); // Nếu chọn "Tất cả", tải lại toàn bộ danh sách nhân viên
    return;
  }

  const trangThaiNumber = Number(this.filterTrangThai); // Chuyển đổi thành số
  this.nhanVienService.filterNhanVienByTrangThai(trangThaiNumber).subscribe({
    next: (data) => {
      this.nhanViens = data;
    },
    error: (err) => {
      console.error('Lỗi khi lọc nhân viên:', err);
      alert('Không thể lọc danh sách nhân viên!');
    }
  });
}
  convertToNumber(): void {
    this.filterTrangThai = Number(this.filterTrangThai); // Chuyển về số
  }
  
  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.currentNhanVien.anh = file.name;
    }
  }

  private initializeNhanVien(): NhanVienRequest {
    return {
      ma: '',
      ten: '',
      gioiTinh: true,
      ngaySinh: '',
      email: '',
      soDienThoai: '',
      taiKhoan: '',
      matKhau: '',
      anh: '',
      ngayTao: this.getCurrentLocalDateTime(),
      ngaySua: null,
      trangThai: 1,
      idVaiTro: 0
    };
  }

  private getCurrentLocalDateTime(): string {
    const now = new Date();
    return now.toISOString().slice(0, 19); // Định dạng YYYY-MM-DDTHH:MM:SS
  }
}
