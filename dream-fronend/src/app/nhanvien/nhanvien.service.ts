import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NhanVienService {
  private apiUrl = 'http://localhost:8080/api/nhan-vien';

  constructor(private http: HttpClient) {}

  // Lấy danh sách nhân viên
  getNhanVien(): Observable<NhanVien[]> {
    return this.http.get<NhanVien[]>(`${this.apiUrl}/hien-thi`);
  }

  // Tìm kiếm nhân viên theo tên
  searchNhanVienByName(ten: string): Observable<NhanVien[]> {
    return this.http.get<NhanVien[]>(`${this.apiUrl}/tim-kiem?ten=${ten}`);
  }

  // Lọc nhân viên theo trạng thái
  filterNhanVienByTrangThai(trangThai: number | ''): Observable<NhanVien[]> {
    if (trangThai === '') {
      return this.getNhanVien(); // Nếu chọn "Tất cả", gọi API lấy toàn bộ danh sách
    }
    return this.http.get<NhanVien[]>(`${this.apiUrl}/loc-trang-thai?trangThai=${trangThai}`);
  }
  

  // Thêm nhân viên
  addNhanVien(nhanVienRequest: NhanVienRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/add`, nhanVienRequest);
  }

  // Sửa nhân viên
  updateNhanVien(nhanVienRequest: NhanVienRequest): Observable<any> {
    return this.http.put(`${this.apiUrl}/update`, nhanVienRequest);
  }

  // Lấy danh sách vai trò (liên quan đến nhân viên)
  getVaiTro(): Observable<VaiTro[]> {
    const vaiTroApi = 'http://localhost:8080/api/vai-tro/hien-thi';
    return this.http.get<VaiTro[]>(vaiTroApi);
  }

  // Thêm vai trò (liên quan đến nhân viên)
  addVaiTro(vaiTroRequest: VaiTroRequest): Observable<any> {
    const vaiTroApi = 'http://localhost:8080/api/vai-tro/add';
    return this.http.post(vaiTroApi, vaiTroRequest);
  }

  // Sửa vai trò (liên quan đến nhân viên)
  updateVaiTro(vaiTroRequest: VaiTroRequest): Observable<any> {
    const vaiTroApi = 'http://localhost:8080/api/vai-tro/update';
    return this.http.put(vaiTroApi, vaiTroRequest);
  }
}

export interface NhanVien {
  id: number;
  ma: string;
  ten: string;
  gioiTinh: boolean;
  ngaySinh: string;
  email: string;
  soDienThoai: string;
  taiKhoan: string;
  matKhau: string;
  anh: string;
  ngayTao: string | null;  // Hỗ trợ null khi thêm mới
  ngaySua: string | null;  // Hỗ trợ null khi thêm mới
  trangThai: number;
  idVaiTro: number;
  tenVaiTro: string;
}

export interface NhanVienRequest {
  id?: number;
  ma: string;
  ten: string;
  gioiTinh: boolean;
  ngaySinh: string;
  email: string;
  soDienThoai: string;
  taiKhoan: string;
  matKhau: string;
  anh: string;
  ngayTao: string | null;
  ngaySua: string | null;
  trangThai: number;
  idVaiTro: number;
}

// Interface cho VaiTro
export interface VaiTro {
  id: number;
  ten: string;
  trangThai: number;
}

// Interface cho VaiTroRequest
export interface VaiTroRequest {
  id?: number;
  ten: string;
  trangThai: number;
}
