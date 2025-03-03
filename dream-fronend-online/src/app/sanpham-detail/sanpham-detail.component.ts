import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { SanphamDetailService } from './sanpham-detail.service';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-sanpham-detail',
  standalone: true,
  imports: [CommonModule, RouterModule, HeaderComponent,FormsModule],
  templateUrl: './sanpham-detail.component.html',
  styleUrl: './sanpham-detail.component.css'
})
export class SanphamDetailComponent implements OnInit {
  sanPham: any = {};
  danhSachSize: any[] = [];
  danhSachMauSac: any[] = [];
  selectedSize: string = '';
  selectedMauSac: string = '';
  soLuongMua: number = 1;

  private route = inject(ActivatedRoute); 
  private sanphamService = inject(SanphamDetailService);

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.loadSanPhamChiTiet();
    this.loadSize();
    this.loadMauSac();
  }

  loadSanPhamChiTiet(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.sanphamService.getSanPhamById(id).subscribe({
        next: (data) => {
          console.log("Dữ liệu sản phẩm nhận được:", data);
          this.sanPham = data;
          
        },
        error: (err) => console.error("Lỗi khi lấy dữ liệu sản phẩm:", err)
      });
    }
}

  loadMauSac(): void {
    this.sanphamService.getMauSac().subscribe({
      next: (data) => {
        console.log("Dữ liệu màu sắc:", data); // Kiểm tra dữ liệu nhận được
        this.danhSachMauSac = data;
        if (data.length > 0) {
          this.selectedMauSac = data[0].ten;
        }
      },
      error: (err) => console.error("Lỗi khi lấy danh sách màu sắc:", err)
    });
  }
  
  loadSize(): void {
    this.sanphamService.getSizes().subscribe({
      next: (data) => {
        console.log("Dữ liệu kích thước:", data); // Kiểm tra dữ liệu nhận được
        this.danhSachSize = data;
        if (data.length > 0) {
          this.selectedSize = data[0].ten;
        }
      },
      error: (err) => console.error("Lỗi khi lấy danh sách size:", err)
    });
  }  

  giamSoLuong() {
    if (this.soLuongMua > 1) {
      this.soLuongMua--;
    }
  }

  tangSoLuong() {
    this.soLuongMua++;
  }
}
