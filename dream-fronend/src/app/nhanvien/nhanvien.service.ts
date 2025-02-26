import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NhanVienService {
  private apiUrl = 'http://localhost:8080/api'; // URL backend

  constructor(private http: HttpClient) {}

  // 🟢 Nhân viên API

  // Lấy danh sách nhân viên có phân trang
  getNhanVien(page: number, size: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/nhan-vien/hien-thi?page=${page}&size=${size}`);
  }

  // Thêm nhân viên mới
  addNhanVien(nhanVien: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/nhan-vien/add`, nhanVien);
  }

  // Lấy chi tiết nhân viên theo ID
  getNhanVienDetail(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/nhan-vien/${id}`);
  }

  // Cập nhật thông tin nhân viên
  updateNhanVien(nhanVien: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/nhan-vien/update`, nhanVien);
  }

  // 🔎 Tìm kiếm nhân viên theo tên
  searchNhanVienByName(name: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/nhan-vien/search?ten=${name}`);
  }

  // 🟢 Vai trò API

  // Lấy danh sách tất cả vai trò
  getVaiTros(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/vai-tro/hien-thi`);
  }
  // ✅ Thêm vai trò
  addVaiTro(vaiTro: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/vai-tro/add`, vaiTro);
  }

  // ✅ Cập nhật vai trò
  updateVaiTro(vaiTro: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/vai-tro/update`, vaiTro);
  }
}
