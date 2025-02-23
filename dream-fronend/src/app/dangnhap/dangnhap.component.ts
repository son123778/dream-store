import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DangNhapService } from './dangnhap.service';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-dangnhap',
  imports: [CommonModule, FormsModule],
  templateUrl: './dangnhap.component.html',
  styleUrl: './dangnhap.component.css',
})
export class DangnhapComponent implements OnInit {
  errors: any = {};
  tentaikhoan: string='';
  matkhau: string='';
  khachhang: any = null;
  nhanvien: any=null;
constructor(private dangNhapService: DangNhapService) { }
  ngOnInit(): void {
  }
  kiemTraTaiKhoan(){
    // if (!this.validateForm()) {
    //   return; 
    // }

    this.khachhang=null;
    
        this.dangNhapService.getKhachHangBySoDienThoai(this.tentaikhoan).subscribe(
          (data)=>{this.khachhang=data}
        );
      
      
      alert('khach hang');
    
    
  }
  kiemTraNhanVien(): boolean{
    this.errors = {};
    

    return Object.keys(this.errors).length === 0;
  }
}