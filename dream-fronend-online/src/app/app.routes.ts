import { Routes } from '@angular/router';
import { BanhangComponent } from './banhang/banhang.component';
import { SanphamDetailComponent } from './sanpham-detail/sanpham-detail.component';

// Các routes được cấu hình cho ứng dụng
export const routes: Routes = [
  { path: '', component: BanhangComponent }, // Trang chính là danh sách sản phẩm
  { path: 'sanpham/:id', component: SanphamDetailComponent }, // Chi tiết sản phẩm
  { path: '**', redirectTo: '' } // Nếu không tìm thấy trang thì về trang chính
];
