import { Component, OnInit } from '@angular/core';
import { NhanvienService } from './nhanvien.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-nhanvien',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './nhanvien.component.html',
  styleUrls: ['./nhanvien.component.css']
})
export class NhanvienComponent implements OnInit {
  state: 'list' | 'form' = 'list';
  isEditing: boolean = false;
  searchQuery: string = '';
  statusFilter: string = '';
  filteredNhanViens: any[] = [];
  currentNhanVien: any = null;

  constructor(private nhanVienService: NhanvienService) {}

  ngOnInit(): void {
    this.fetchNhanViens();
  }

  fetchNhanViens(): void {
    const status = this.statusFilter ? Number(this.statusFilter) : undefined;
    this.nhanVienService.getNhanVien(status, this.searchQuery).subscribe((data) => {
      this.filteredNhanViens = data;
    });
  }

  applyFilter(): void {
    this.fetchNhanViens();
  }

  showCreateForm(): void {
    this.state = 'form';
    this.isEditing = false;
    const now = new Date().toISOString().slice(0, 16); // ISO 8601 format for datetime-local
    this.currentNhanVien = {
      idVaiTro: null,
      ma: '',
      ten: '',
      gioiTinh: true,
      ngaySinh: '',
      email: '',
      soDienThoai: '',
      taiKhoan: '',
      matKhau: '', 
      anh: '',
      trangThai: 1,
      ngayTao: now,
      ngaySua: null,
    };
  }

  showEditForm(nv: any): void {
    this.state = 'form';
    this.isEditing = true;
    this.currentNhanVien = { ...nv };
    const now = new Date().toISOString().slice(0, 16); // Cập nhật thời gian sửa hiện tại
    this.currentNhanVien.ngaySua = now;
  }

  saveNhanVien(): void {
    if (this.isEditing) {
      // Gửi lên server khi sửa
      this.nhanVienService.updateNhanVien(this.currentNhanVien.id, this.currentNhanVien).subscribe(() => {
        this.fetchNhanViens();
        this.backToList();
      });
    } else {
      // Gửi lên server khi thêm mới
      this.nhanVienService.createNhanVien(this.currentNhanVien).subscribe(() => {
        this.fetchNhanViens();
        this.backToList();
      });
    }
  }

  backToList(): void {
    this.state = 'list';
    this.currentNhanVien = null;
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.currentNhanVien.anh = file.name;
    }
  }
}