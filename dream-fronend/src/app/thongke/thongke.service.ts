import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface TopSanPhamResponse {
  tenSanPham: string;  // Tên sản phẩm
  tongSoLuong: number; // Tổng số lượng bán được
}

export interface ThongKeHomNayResponse {
  soHoaDon: number;         // Số hóa đơn trong ngày hôm nay
  tongDoanhThu: number;     // Tổng doanh thu trong ngày hôm nay
  soKhachHang: number;      // Số khách hàng trong ngày hôm nay
}
export interface ThongKeResponse {
  soHoaDon: number;
  tongDoanhThu: number;
  soKhachHang: number;
}

export interface ThongKeThangResponse {
  thang: number; // Có thể là tháng hoặc năm, tùy vào API trả về
  tongDoanhThu: number;
}
export interface ThongKeThangNayResponse {
  ngay: number; // Có thể là tháng hoặc năm, tùy vào API trả về
  tongDoanhThu: number;
}
export interface ThongKeRequest {
  startDate: string | null;
  endDate: string | null;
}

@Injectable({
  providedIn: 'root',
})
export class ThongKeService {
  private apiUrl = 'http://localhost:8080/api/thong-ke';

  constructor(private http: HttpClient) {}

  // Lấy dữ liệu thống kê tổng quan
  thongKeTongQuan(type: string): Observable<ThongKeResponse> {
    return this.http.get<ThongKeResponse>(`${this.apiUrl}/${type}`);
  }

  // Lấy dữ liệu thống kê doanh thu từng tháng trong năm nay
  thongKeTungThangNam(): Observable<ThongKeThangResponse[]> {
    return this.http.get<ThongKeThangResponse[]>(`${this.apiUrl}/nam-nay/thang`);
  }

  // Lấy dữ liệu thống kê doanh thu theo từng năm
  thongKeTungNam(): Observable<ThongKeThangResponse[]> {
    return this.http.get<ThongKeThangResponse[]>(`${this.apiUrl}/tat-ca/nam`);
  }

  // Lấy dữ liệu thống kê doanh thu theo từng ngày trong tháng
  thongKeTungNgayTrongThang(): Observable<ThongKeThangNayResponse[]> {
    return this.http.get<ThongKeThangNayResponse[]>(`${this.apiUrl}/thang-nay/ngay`);
  }
  // Lấy dữ liệu thống kê doanh thu ngày hôm nay
  thongKeHomNay(): Observable<ThongKeHomNayResponse> {
    return this.http.get<ThongKeHomNayResponse>(`${this.apiUrl}/hom-nay`);
  }


   // Lấy top sản phẩm bán chạy trong ngày hôm nay
   topSanPhamHomNay(page: number, size: number): Observable<TopSanPhamResponse[]> {
    return this.http.get<TopSanPhamResponse[]>(`${this.apiUrl}/hom-nay/top-san-pham?page=${page}&size=${size}`);
  }

  // Lấy top sản phẩm bán chạy trong tháng này
  topSanPhamThangNay(page: number, size: number): Observable<TopSanPhamResponse[]> {
    return this.http.get<TopSanPhamResponse[]>(`${this.apiUrl}/thang-nay/top-san-pham?page=${page}&size=${size}`);
  }

  // Lấy top sản phẩm bán chạy trong năm nay
  topSanPhamNamNay(page: number, size: number): Observable<TopSanPhamResponse[]> {
    return this.http.get<TopSanPhamResponse[]>(`${this.apiUrl}/nam-nay/top-san-pham?page=${page}&size=${size}`);
  }

  // Lấy top sản phẩm bán chạy tất cả thời gian
  topSanPhamTatCa(page: number, size: number): Observable<TopSanPhamResponse[]> {
    return this.http.get<TopSanPhamResponse[]>(`${this.apiUrl}/tat-ca/top-san-pham?page=${page}&size=${size}`);
  }
}
