import { Routes } from '@angular/router';
import { BanhangComponent } from './banhang/banhang.component';

// Các routes được cấu hình cho ứng dụng
export const routes: Routes = [
  {
    path: '',
    component: BanhangComponent
  },
  { path: '**', redirectTo: '' },
];
