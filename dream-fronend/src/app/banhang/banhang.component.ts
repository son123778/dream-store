import { Component, ViewChild, ElementRef, AfterViewInit, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-banhang',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './banhang.component.html',
  styleUrl: './banhang.component.css'
})
export class BanhangComponent implements AfterViewInit {
  invoices: number[] = [1]; // Mặc định có một hóa đơn
  selectedTab: number = 0; // Tab hiện tại
  tabWidth: number = 120; // Kích thước tab
  readonly minTabWidth: number = 50; // Kích thước tối thiểu
  readonly maxTabWidth: number = 120; // Kích thước tối đa
  selectedButton: string = '';
  isQuickSaleOpen = false; // Trạng thái cửa sổ bán nhanh
  isNormalSaleOpen = false; // Trạng thái cửa sổ bán thường

  @ViewChild('tabContainer', { static: false }) tabContainer!: ElementRef;

  ngAfterViewInit(): void {
    this.updateTabSize();
  }

  /** Chọn tab */
  selectTab(index: number): void {
    this.selectedTab = index;
  }

  /** Tạo hóa đơn mới */
  createInvoice(): void {
    const newInvoice = this.invoices.length > 0 ? Math.max(...this.invoices) + 1 : 1;
    this.invoices.push(newInvoice);
    this.selectedTab = this.invoices.length - 1;
    this.updateTabSize();
  }

  /** Xóa hóa đơn */
  removeInvoice(index: number, event: Event): void {
    event.stopPropagation();
    this.invoices.splice(index, 1);
    this.selectedTab = Math.max(0, this.invoices.length - 1);
    this.updateTabSize();
  }

  /** Cập nhật kích thước tab khi số lượng thay đổi */
  updateTabSize(): void {
    if (!this.tabContainer) return;

    const containerWidth = window.innerWidth * 0.5; // Giới hạn 50% màn hình
    const totalTabs = this.invoices.length + 1; // Tổng số tab + nút thêm

    this.tabWidth = totalTabs * this.maxTabWidth > containerWidth
      ? Math.max(containerWidth / totalTabs, this.minTabWidth)
      : this.maxTabWidth;
  }

  /** Lắng nghe thay đổi kích thước màn hình */
  @HostListener('window:resize')
  onResize(): void {
    this.updateTabSize();
  }

  /** Mở giao diện Bán Nhanh và đóng Bán Thường */
  openQuickSale(): void {
    this.isQuickSaleOpen = true;
    this.isNormalSaleOpen = false; // Đảm bảo chỉ mở một cửa sổ
    this.selectedButton = 'quick-sale';
  }

  /** Đóng giao diện Bán Nhanh */
  closeQuickSale(): void {
    this.isQuickSaleOpen = false;
    this.selectedButton = '';
  }

  /** Mở giao diện Bán Thường và đóng Bán Nhanh */
  openNormalSale(): void {
    this.isNormalSaleOpen = true;
    this.isQuickSaleOpen = false; // Đảm bảo chỉ mở một cửa sổ
    
    this.selectedButton = 'normal-sale'; // Đánh dấu nút Bán Thường đang được chọn
  }

  /** Đóng giao diện Bán Thường */
  closeNormalSale(): void {
    this.isNormalSaleOpen = false;
    this.selectedButton = ''; 
  }
}
