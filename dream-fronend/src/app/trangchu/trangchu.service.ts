import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TrangChuService {
    private apiUrl = 'http://localhost:8080/api'; // URL backend
    
    constructor(private http: HttpClient) {}
    // Lấy danh sách sản phẩm từ backend
    // hàm sản phẩm
    
  }