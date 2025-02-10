import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class KhachHangService {
  private apiUrl = 'http://localhost:8080/api/khach-hang'; // URL backend

  constructor(private http: HttpClient) {}

  // Lấy danh sách nhân viên
  getAllKhachHang(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl + '/hien-thi');
  }

  // Lấy thông tin chi tiết nhân viên theo ID
  getKhachHangById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }
}