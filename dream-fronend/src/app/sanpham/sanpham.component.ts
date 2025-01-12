import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SanphamService } from './sanpham.service';

@Component({
  selector: 'app-sanpham',
  imports: [CommonModule],
  templateUrl: './sanpham.component.html',
  styleUrls: ['./sanpham.component.css']
})
export class SanphamComponent implements OnInit {
  sanPhams: any[] = [];
  thuongHieus: any[] = [];
  chatLieus: any[] = [];
  coAos: any[] = [];
  xuatXus: any[] = [];
  showModal: boolean = false; // Trạng thái để kiểm tra modal hiển thị hay không

  constructor(private sanphamService: SanphamService) {}

  ngOnInit(): void {
    this.loadData()
  }

  // hàm load 
  loadData() : void{
  this.listSanPham()
  this.listThuongHieu()
  this.listChatLieu()
  this.listCoAo()
  this.listXuatXu()
  }

  listSanPham(): void {
    this.sanphamService.getSanPham().subscribe(data => {
      console.log("data", data);
      this.sanPhams = Array.isArray(data.content) ? data.content : [];
    });
  }
  
  listThuongHieu(): void {
    this.sanphamService.getThuongHieu().subscribe(data => {
      console.log("Thương Hiệu Data", data); // Log dữ liệu
      this.thuongHieus = Array.isArray(data) ? data : []; // Gán dữ liệu từ response
    }, error => {
      console.error("Lỗi khi gọi API thương hiệu", error); // Log lỗi nếu có
    });
  }
  
  
  
  listChatLieu(): void {
    this.sanphamService.getChatLieu().subscribe(data => {
      console.log("Chất Liệu Data", data);
      this.chatLieus = Array.isArray(data) ? data : []; // Gán dữ liệu đúng từ thuộc tính content
    });
  }
  
  listCoAo(): void {
    this.sanphamService.getCoAo().subscribe(data => {
      console.log("Cổ Áo Data", data);
      this.coAos = Array.isArray(data) ? data : []; // Gán dữ liệu đúng từ thuộc tính content
    });
  }
  
  listXuatXu(): void {
    this.sanphamService.getXuatXu().subscribe(data => {
      console.log("Xuất Xứ Data", data);
      this.xuatXus = Array.isArray(data) ? data : []; 
    });
  }  
  // Hàm mở modal
  openModal() {
    this.showModal = true;
  }

  // Hàm đóng modal
  closeModal() {
    this.showModal = false;
  }
}
