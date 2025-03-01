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
  sanPhamOnlines: any[] = [];
  totalPages: number = 0;
  currentPage: number = 0;
  size: number = 20;
  modalCard: boolean = false;

  constructor(private banHangService : BanhangService) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.loadSanPhamOnline(0);
  }

  cardModal(): void {
    this.modalCard = !this.modalCard;
  }

  loadSanPhamOnline(page: number): void {
    this.banHangService.getSanPhamOnline(page, this.size).subscribe(
      (data) => {
        this.sanPhamOnlines = data.content; // Dữ liệu sản phẩm
        this.totalPages = data.totalPages; // Tổng số trang
        this.currentPage = page;
      },
      (error) => {
        console.error('Lỗi khi gọi API:', error);
      }
    );
  }
  nextPage(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.loadSanPhamOnline(this.currentPage + 1);
    }
  }

  prevPage(): void {
    if (this.currentPage > 0) {
      this.loadSanPhamOnline(this.currentPage - 1);
    }
  }
}
