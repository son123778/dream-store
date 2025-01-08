import { Routes } from '@angular/router';

// Import các component được sử dụng trong ứng dụng
import { BanhangComponent } from './banhang/banhang.component';
import { SanphamComponent } from './sanpham/sanpham.component';
import { LayoutComponent } from './layout/layout.component';
import { KhachhangComponent } from './khachhang/khachhang.component';
import { KhuyenmaiComponent } from './khuyenmai/khuyenmai.component';
import { NhanvienComponent } from './nhanvien/nhanvien.component';
import { ThongkeComponent } from './thongke/thongke.component';
import { VoucherComponent } from './voucher/voucher.component';

// Định nghĩa các routes
export const routes: Routes = [
  {
    path: '', // Đây là layout chính
    component: LayoutComponent,
    children: [
      { path: '', redirectTo: 'banhang', pathMatch: 'full' }, // Route mặc định
      { path: 'banhang', component: BanhangComponent }, // Route Bán hàng
      { path: 'sanpham', component: SanphamComponent }, // Route Sản phẩm
      { path: 'voucher', component: VoucherComponent }, // Route Voucher
      { path: 'khuyenmai', component: KhuyenmaiComponent }, // Route Khuyến mãi
      { path: 'khachhang', component: KhachhangComponent }, // Route Khách hàng
      { path: 'nhanvien', component: NhanvienComponent }, // Route Nhân viên
      { path: 'thongke', component: ThongkeComponent }, // Route Thống kê
    ],
  },
];
