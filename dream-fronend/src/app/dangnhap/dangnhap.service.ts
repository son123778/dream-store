import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams  } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class DangnhapService {
  private apiUrl = 'http://localhost:8080/api/auth'; // Đảm bảo URL backend chính xác

  constructor(private http: HttpClient) { }

  // Phương thức đăng nhập
  login(email: string, password: string): Observable<any> {
    const params = new HttpParams()
      .set('email', email)
      .set('password', password);  // Thêm email và password như tham số trong URL

    // Gửi yêu cầu POST đến API backend với tham số query
    return this.http.post<any>(`${this.apiUrl}/login`, params, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')  // Đặt Content-Type cho form data
    });
  }
}
