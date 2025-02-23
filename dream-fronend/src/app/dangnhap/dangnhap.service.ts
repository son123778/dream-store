import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DangNhapService {
    private apiUrl = 'http://localhost:8080/api'; // URL backend
    
    constructor(private http: HttpClient) {}
    // Lấy danh sách sản phẩm từ backend
    // hàm sản phẩm
    getKhachHangBySoDienThoai(soDienThoai: string): Observable<any[]> {
      return this.http.get<any[]>(`${this.apiUrl}/khac-hang/sdt?soDienThoai=${soDienThoai}`);
    }
    getNhanVienByTaiKoan(taiKhoan: string): Observable<any[]> {
      return this.http.get<any[]>(`${this.apiUrl}/nhan-vien/tk?taiKhoan=${taiKhoan}`);
    }
  }