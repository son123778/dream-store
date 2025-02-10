import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SanphamService {
  // đường dẫn api
  private apiUrl = 'http://localhost:8080/api'; // URL backend

  constructor(private http: HttpClient) {}
  // Lấy danh sách sản phẩm từ backend
  // hàm sản phẩm
  getSanPham(page: number, size: number): Observable<ApiResponseSanPham> {
    return this.http.get<ApiResponseSanPham>(`${this.apiUrl}/san-pham/hien-thi?page=${page}&size=${size}`);
  }  
  
  getSanPhamChiTiet(idSanPham: number, page: number, size: number): Observable<ApiResponseSanPhamChiTiet> {
    return this.http.get<ApiResponseSanPhamChiTiet>(
      `${this.apiUrl}/san-pham-chi-tiet/hien-thi?idSanPham=${idSanPham}&page=${page}&size=${size}`
    );
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
  
  getSize(): Observable<Size[]> {
    return this.http.get<Size[]>(this.apiUrl + '/size/hien-thi').pipe();
  }

  getMauSac(): Observable<MauSac[]> {
    return this.http.get<MauSac[]>(this.apiUrl + '/mau-sac/hien-thi').pipe();
  }

  getXuatXu(): Observable<XuatXu[]> {
    return this.http.get<XuatXu[]>(this.apiUrl + '/xuat-xu/hien-thi').pipe();
  }

  addSanPham(sanPhamRequest: any): Observable<any> {
    return this.http.post(this.apiUrl + '/san-pham/add', sanPhamRequest);
  }

  updateSanPham(sanPhamRequest: any): Observable<any> {
    return this.http.put(this.apiUrl + '/san-pham/update', sanPhamRequest);
  }

  addSanPhamChiTiet(sanPhamChiTietRequest: any): Observable<any> {
    return this.http.post(this.apiUrl + '/san-pham-chi-tiet/add', sanPhamChiTietRequest);
  }

  updateSanPhamChiTiet(sanPhamChiTietRequest: any): Observable<any> {
    return this.http.put(this.apiUrl + '/san-pham-chi-tiet/update', sanPhamChiTietRequest);
  }  

  exportExcel(): Observable<Blob> {
    return this.http.get(this.apiUrl + '/san-pham/xuat-excel', { responseType: 'blob' });
  }

  exportExcelSanPhamChiTiet(idSanPham: number): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/san-pham-chi-tiet/xuat-excel?idSanPham=${idSanPham}`, {
      responseType: 'blob'
    });
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
// Interface cho SanPhamChiTiet
export interface SanPhamChiTiet {
  id: number;
  ma: string;
  gia: number;
  soLuong: number;
  ngayTao: string;
  ngaySua: string;
  trangThai: number;
  idSanPham: number;
  tenSanPham: string;
  idSize: number;
  tenSize: string;
  idMauSac: number;
  tenMauSac: string;
}

// Interface cho ApiResponse của SanPhamChiTiet
export interface ApiResponseSanPhamChiTiet {
  content: SanPhamChiTiet[];
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

// thuộc tính
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

export interface Size {
  id: number;
  ma: string;
  ten: string;
}

export interface MauSac {
  id: number;
  ma: string;
  ten: string;
}

export interface ApiResponse<T> {
  content: T[];
}



