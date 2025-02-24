import {Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BanhangService {
  private apiUrl = 'http://localhost:8080/api/ban-hang-online'; // Địa chỉ API backend

  constructor(private http: HttpClient) {}

  getSanPhamOnline(): Observable<any> {
    return this.http.get<any>(this.apiUrl + "/hien-thi");
  }
}
