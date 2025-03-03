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
  errorMessage: string = '';
  loading: boolean = false;

  constructor(
    private dangnhapService: DangnhapService,
    private router: Router
  ) {}

  // Hàm xử lý khi gửi form
  onSubmit(form: NgForm) {
    if (form.invalid) {
      return;
    }

    // Đảm bảo rằng form value có dữ liệu đúng
    const { email, password } = form.value;
    this.loading = true;

    // Gửi yêu cầu đăng nhập đến backend
    this.dangnhapService.login(email, password).subscribe({
      next: (response) => {
        this.loading = false;
        console.log("Đăng nhập thành công:", response); // Log response nếu cần
        alert('Đăng nhập thành công!');
        this.router.navigate(['/layout/banhang']); // Chuyển hướng sau khi đăng nhập thành công
      },
      error: (err) => {
        this.loading = false;
        console.error('Lỗi khi đăng nhập:', err); // Log lỗi nếu có
      }
    });
  }

  // Hàm để xóa thông báo lỗi khi người dùng nhập lại thông tin
  onInputChange(): void {
    this.errorMessage = ''; // Xóa thông báo lỗi khi người dùng bắt đầu nhập lại
  }
}
