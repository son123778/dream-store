import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DangKyService {
  private apiUrl = 'http://localhost:8080/api'; // URL backend
  
  constructor(private http: HttpClient) {}
  // Lấy danh sách sản phẩm từ backend
  // hàm sản phẩm
  
  addKhachHang(khachhang: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/khach-hang/add`, khachhang).pipe(
    );
  }
  getKhachHangByEmail(email: String): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/khach-hang/detail?email=${email}`);
  }
  
 
}