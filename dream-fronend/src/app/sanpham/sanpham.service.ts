import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SanphamService {
  private apiUrl = 'http://localhost:8080/api'; // URL backend

  constructor(private http: HttpClient) {}
  // Lấy danh sách sản phẩm từ backend
  // hàm sản phẩm
  getSanPham(): Observable<ApiResponseSanPham> {
    return this.http.get<ApiResponseSanPham>(this.apiUrl + '/san-pham/hien-thi');
  }  
  getThuongHieu(): Observable<ThuongHieu[]> {
    return this.http.get<ThuongHieu[]>(this.apiUrl + '/thuong-hieu/hien-thi').pipe();
  }
  
  getChatLieu(): Observable<ChatLieu[]> {
    return this.http.get<ChatLieu[]>(this.apiUrl + '/chat-lieu/hien-thi').pipe();
  }
  
  getCoAo(): Observable<CoAo[]> {
    return this.http.get<CoAo[]>(this.apiUrl + '/co-ao/hien-thi').pipe();
  }
  
  getXuatXu(): Observable<XuatXu[]> {
    return this.http.get<XuatXu[]>(this.apiUrl + '/xuat-xu/hien-thi').pipe();
  }
  
}
export interface SanPham {
  id: number;
  ma: string;
  ten: string;
  ngayTao: string;
  ngaySua: string;
  trangThai: number;
  idChatLieu: number;
  tenChatLieu: string;
  idThuongHieu: number;
  tenThuongHieu: string;
  idCoAo: number;
  tenCoAo: string;
  idXuatXu: number;
  tenXuatXu: string;
}

export interface ApiResponseSanPham {
  content: SanPham[];
  pageable: {
    pageNumber: number;
    pageSize: number;
    sort: {
      empty: boolean;
      unsorted: boolean;
      sorted: boolean;
    };
    offset: number;
    unpaged: boolean;
    paged: boolean;
  };
  last: boolean;
  totalElements: number;
  totalPages: number;
  first: boolean;
  size: number;
  number: number;
  numberOfElements: number;
  empty: boolean;
}
export interface ThuongHieu {
  id: number;
  ma: string;
  ten: string;
}

export interface ChatLieu {
  id: number;
  ma: string;
  ten: string;
}

export interface CoAo {
  id: number;
  ma: string;
  ten: string;
}

export interface XuatXu {
  id: number;
  ma: string;
  ten: string;
}

export interface ApiResponse<T> {
  content: T[];
}



