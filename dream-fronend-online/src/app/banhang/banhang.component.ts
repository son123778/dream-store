import { Component, ViewChild, ElementRef, AfterViewInit, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BanhangService } from './banhang.service';
@Component({
  selector: 'app-banhang',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './banhang.component.html',
  styleUrl: './banhang.component.css'
})
export class BanhangComponent{
  sanPhamOnlines: any[] = []; // Biến lưu danh sách sản phẩm
  constructor(private banHangService : BanhangService) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.loadSanPhamOnline();
  }

  loadSanPhamOnline(): void {
    this.banHangService.getSanPhamOnline().subscribe(
      (data) => {
        this.sanPhamOnlines = data;
      },
      (error) => {
        console.error('Lỗi khi gọi API:', error);
      }
    );
  }
}
