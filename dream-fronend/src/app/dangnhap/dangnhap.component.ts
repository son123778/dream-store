import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { DangnhapService } from './dangnhap.service'; 
import { FormsModule,NgForm } from '@angular/forms';

@Component({
  selector: 'app-dangnhap',
  imports: [CommonModule, FormsModule],  // Add this
  templateUrl: './dangnhap.component.html',
  styleUrl: './dangnhap.component.css'
})
export class DangnhapComponent {
  loginData = {
    email: '',
    password: ''
  };
  loading: boolean = false;
  submitted: boolean = false; // Biến kiểm tra đã nhấn submit chưa
  errors: { email: string; password: string } = { email: '', password: '' }; // Chứa tất cả lỗi
  storedUserData: any = {
  }; // Lưu thông tin tài khoản từ backend để so sánh
  constructor(
    private dangnhapService: DangnhapService,
    private router: Router
  ) {}

  // Kiểm tra định dạng email
  isValidEmail(email: string): boolean {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
  }
  onInputChange(field: string) {
    if (this.submitted) {
      // Khi người dùng nhập lại dữ liệu, kiểm tra và xóa lỗi nếu hợp lệ
      if (field === 'email') {
        if(this.loginData.email.length==1){
          this.errors.email = ''; 
        }
      else  if (this.isValidEmail(this.loginData.email)) {
          this.errors.email = '';  // Xóa lỗi mật khẩu nếu nhập đúng
        }
      }
  
    else  if (field === 'password') {
        if (this.loginData.password.length==1) {
          this.errors.password = '';  // Xóa lỗi mật khẩu nếu nhập đúng
        }
      }
    }
  }
  
  // Xử lý khi nhấn đăng nhập
  onSubmit(form: NgForm) {
    this.submitted = true; // Đánh dấu đã submit
    this.errors = { email: '', password: '' }; // Reset lỗi

    // Kiểm tra email
    if (!this.loginData.email) {
      this.errors.email = 'Vui lòng nhập email!';
    } else if (!this.isValidEmail(this.loginData.email)) {
      this.errors.email = 'Email không đúng định dạng!';
    }

    // Kiểm tra mật khẩu
    if (!this.loginData.password) {
      this.errors.password = 'Vui lòng nhập mật khẩu!';
    } else if (this.loginData.password.length < 5) {
      this.errors.password = 'Mật khẩu phải có ít nhất 8 ký tự!';
    }

    // Nếu có lỗi, dừng lại
    if (this.errors.email || this.errors.password) {
      return;
    }

    // Bắt đầu đăng nhập
    this.loading = true;
    this.dangnhapService.login(this.loginData.email, this.loginData.password).subscribe({
      next: (response) => {
        this.loading = false;
        console.log("dau ra",this.storedUserData.email)
        console.log("dau ra",response)
        // So sánh dữ liệu nhập vào với dữ liệu từ backend
       
            alert('Đăng nhập thành công!');
            this.router.navigate(['/layout/banhang']);
      },
      error: (err) => {
        this.loading = false;
        console.error("Lỗi hệ thống: ", err);  // In ra chi tiết lỗi
        if (err.status === 401) {
          this.errors.password = 'Sai mật khẩu'; // Mật khẩu sai
        } else if (err.status === 404) {
          this.errors.email = 'Email không tồn tại'; // Email không tồn tại
        } else {
          this.errors.email = 'Lỗi hệ thống. Vui lòng thử lại!';
        }
      }
    });
  }
}
