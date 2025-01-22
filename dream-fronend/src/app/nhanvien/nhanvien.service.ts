import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NhanvienService {
  private baseUrl = 'http://localhost:8080/api/nhanvien';

  constructor(private http: HttpClient) {}

  getNhanVien(status?: number, keyword?: string): Observable<any[]> {
    let params = new HttpParams();
    if (status !== undefined) params = params.set('trangThai', status.toString());
    if (keyword) params = params.set('keyword', keyword);
    return this.http.get<any[]>(this.baseUrl, { params });
  }

  createNhanVien(data: any): Observable<any> {
    return this.http.post<any>(this.baseUrl, data);
  }

  updateNhanVien(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${id}`, data);
  }  
}