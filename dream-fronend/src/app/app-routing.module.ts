import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NhanvienComponent } from './nhanvien/nhanvien.component';

const routes: Routes = [
  { path: 'nhanvien', component: NhanvienComponent },
  { path: '', redirectTo: '/nhanvien', pathMatch: 'full' }, // Redirect mặc định
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
