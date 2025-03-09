import { Component, ViewChild, ElementRef, AfterViewInit, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {HoaDonService} from './hoadon.service';
import {response} from 'express';

@Component({
  selector: 'app-hoadon',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './hoadon.component.html',
  styleUrl: './hoadon.component.css'
})
export class HoaDonComponent implements AfterViewInit {
  hoaDons: any[] = [];
  page: number = 1;
  pageSize: number = 10;
  totalRecords: number = 0;

  constructor(private hoaDonService: HoaDonService) {}

  ngOnInit(): void {
    this.loadHoaDons();
  }

  loadHoaDons(): void {
    const request = {
      idHoaDon: null,
      maHoaDon: null,
      tenKhachHang: null,
      tenNhanVien: null,
      ngayTaoFrom: null,
      ngayTaoTo: null,
      listTrangThai: [],
      totalRecords: 0,
      page: this.page,
      pageSize: this.pageSize
    };

    this.hoaDonService.getHoaDons(request).subscribe(response => {
      this.hoaDons = response.data;
      this.totalRecords = response.totalRecords;
    });
  }

  onPageChange(newPage: number): void {
    this.page = newPage;
    this.loadHoaDons();
  }

  ngAfterViewInit(): void {
  }

  searchParams = {
    idHoaDon: null,
    maHoaDon: null,
    tenKhachHang: null,
    tenNhanVien: null,
    ngayTaoFrom: null,
    ngayTaoTo: null,
    listTrangThai: []
  };

  fetchHoaDons() {
    const request = {
      ...this.searchParams,
      ngayTaoFrom: this.searchParams.ngayTaoFrom ? new Date(this.searchParams.ngayTaoFrom).toISOString() : null
    };
    this.hoaDonService.getHoaDons(request).subscribe(
      (data) => {
        this.hoaDons = data.content || data;
        console.log("Dữ liệu từ API:", this.hoaDons);
      },
      (error) => {
        console.error("Lỗi API:", error);
      }
    );
  }


}
