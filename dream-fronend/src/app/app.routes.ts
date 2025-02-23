import { Routes } from '@angular/router';
import { BanhangComponent } from './banhang/banhang.component';
import { SanphamComponent } from './sanpham/sanpham.component';
import { LayoutComponent } from './layout/layout.component';
import { KhachhangComponent } from './khachhang/khachhang.component';
import { KhuyenmaiComponent } from './khuyenmai/khuyenmai.component';
import { NhanvienComponent } from './nhanvien/nhanvien.component';
import { ThongkeComponent } from './thongke/thongke.component';
import { VoucherComponent } from './voucher/voucher.component';
import { TrangchuComponent } from './trangchu/trangchu.component';
import { DangkyComponent } from './dangky/dangky.component';
import { DangnhapComponent } from './dangnhap/dangnhap.component';

import { TestComponent } from './test/test.component';
// Các routes được cấu hình cho ứng dụng
export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', redirectTo: 'banhang', pathMatch: 'full' }, // Route mặc định
      { path: 'banhang', component: BanhangComponent }, // Bán hàng
      { path: 'sanpham', component: SanphamComponent }, // Sản phẩm
      { path: 'voucher', component: VoucherComponent }, // Voucher
      { path: 'khuyenmai', component: KhuyenmaiComponent }, // Khuyến mãi
      { path: 'khachhang', component: KhachhangComponent }, // Khách hàng
      { path: 'nhanvien', component: NhanvienComponent }, // Nhân viên
      { path: 'thongke', component: ThongkeComponent }, // Thống kê
      
    ],
  },
  {
    path: '',
    children: [
      { path: '', redirectTo: 'trangchu', pathMatch: 'full' }, // Route mặc định
      { path: 'trangchu', component: TrangchuComponent }, // Trang chủ
      { path: 'dangnhap', component: DangnhapComponent }, // Đăng nhập
      { path: 'dangky', component: DangkyComponent }, // Đăng ký
      { path: 'test', component: TestComponent }, // Khách hàng
      
    ],
  },
  { path: '**', redirectTo: '' }, // Redirect tất cả các đường dẫn không hợp lệ
];
