import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ThongKeResponse {
  soHoaDon: number;
  tongDoanhThu: number;
  soKhachHang: number;
}

export interface ThongKeThangResponse {
  thang: number; // Có thể là tháng hoặc năm, tùy vào API trả về
  tongDoanhThu: number;
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
}
