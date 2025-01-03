import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-sanpham',
  imports: [CommonModule],
  templateUrl: './sanpham.component.html',
  styleUrl: './sanpham.component.css'
})
export class SanphamComponent {
  showModal: boolean = false; // Trạng thái để kiểm tra modal hiển thị hay không

  // Hàm mở modal
  openModal() {
    this.showModal = true;
  }

  // Hàm đóng modal
  closeModal() {
    this.showModal = false;
  }
}
