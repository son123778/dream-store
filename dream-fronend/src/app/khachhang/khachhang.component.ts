import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common'; // Import CommonModule
import { KhachHangService } from './khachhang.service'; // Import service

@Component({
  selector: 'app-khachhang',
  imports: [],
  templateUrl: './khachhang.component.html',
  styleUrl: './khachhang.component.css'
})
export class KhachhangComponent implements OnInit{
  listKhachHang: any[] = []; // Danh sách khách hàng
  error: string | null = null; // Lỗi nếu có

  constructor(private khachHangService: KhachHangService) {}

  ngOnInit(): void {
    this.loadKhachHang();
  }

  /**
   * Hàm tải danh sách nhân viên từ backend
   */
  loadKhachHang(): void {
    this.khachHangService.getAllKhachHang().subscribe({
      next: (data) => {
        this.listKhachHang = data;
        console.log('Danh sách khách hàng:', this.listKhachHang);
      },
      error: (err) => {
        this.error = 'Không thể tải danh sách khách hàng.';
        console.error('Lỗi khi tải khách hàng:', err);
      },
    });
  }
}
