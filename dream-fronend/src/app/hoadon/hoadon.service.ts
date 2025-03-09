import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HoaDonService {
  private apiUrl = 'http://localhost:8080/api/hoa-don';

  constructor(private http: HttpClient) {}

  getHoaDons(request: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/all`, request);
  }

}
