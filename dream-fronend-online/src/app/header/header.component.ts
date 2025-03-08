import { Component, OnInit } from '@angular/core';
import { HeaderService } from './header.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
    imports: [CommonModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  modalCard: boolean = false;
  gioHang: any[] = []; // Danh sách sản phẩm trong giỏ hàng
  idKhachHang: number = 1; // Giả sử ID khách hàng là 1

  
  constructor(private headerService: HeaderService) {}

  ngOnInit(): void {
    this.loadGioHang();

    this.headerService.gioHangUpdated$.subscribe(() => {
      this.loadGioHang(); // Cập nhật giỏ hàng ngay lập tức
  });

  }

  loadGioHang(): void {
    this.headerService.getGioHang(this.idKhachHang).subscribe((data) => {
      this.gioHang = data;
    });
  }

  xoaSanPham(id: number) {
    this.headerService.deleteFromCart(id).subscribe(() => {
      this.headerService.notifyGioHangUpdated(); // Cập nhật giỏ hàng sau khi xoá
    });
  }

  suaSoLuong(id: number, soLuongMoi: number) {
    this.headerService.updateSoLuong(id, soLuongMoi).subscribe(() => {
      this.headerService.notifyGioHangUpdated(); // Cập nhật giỏ hàng sau khi thay đổi số lượng
    });
  }

  getTongTien(): number {
    return this.gioHang.reduce((total, item) => {
      // console.log(`Sản phẩm: ${item.tenSanPham} - Đơn giá đã nhân số lượng: ${item.donGia}`);
      return total + item.donGia; 
    }, 0);
  }
  
  cardModal(event: Event): void {
    event.preventDefault();
    event.stopPropagation();
    this.modalCard = !this.modalCard;
  }
  // hiện modal thanh toán
  openModalThanhToan() {
    console.log("Nút Thanh toán được ấn!"); // Debug
    this.headerService.openModalThanhToan();
  }
}
