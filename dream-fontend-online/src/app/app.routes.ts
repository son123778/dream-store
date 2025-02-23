
import { Routes } from '@angular/router';
import { BanhangonlineComponent } from './banhangonline/banhangonline.component';
import { HoadonComponent } from './hoadon/hoadon.component';

// Các routes được cấu hình cho ứng dụng
export const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', redirectTo: 'banhangonline', pathMatch: 'full' }, // Route mặc định
      { path: 'banhangonline', component: BanhangonlineComponent }, // Bán hàng
      {path:'hoadon',component:HoadonComponent}
     
    ],
  },
  { path: '**', redirectTo: '' }, // Redirect tất cả các đường dẫn không hợp lệ
  
];

