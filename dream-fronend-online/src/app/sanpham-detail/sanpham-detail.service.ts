import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SanphamDetailService {
  private apiUrl = 'http://localhost:8080/api/ban-hang-online';
  private apiSize = 'http://localhost:8080/api/size/hien-thi';
  private apiMauSac = 'http://localhost:8080/api/mau-sac/hien-thi';

  constructor(private http: HttpClient) {}

  getSanPhamById(id: string): Observable<any> {
    console.log("Gọi API:", `${this.apiUrl}/hien-thi-spct/${id}`);
    return this.http.get<any>(`${this.apiUrl}/hien-thi-spct/${id}`);
  }

  getSizes(): Observable<any[]> {
    return this.http.get<any[]>(this.apiSize);
  }

  getMauSac(): Observable<any[]> {
    return this.http.get<any[]>(this.apiMauSac);
  }
  
}
