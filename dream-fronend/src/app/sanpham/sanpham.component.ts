import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SanphamService } from './sanpham.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-sanpham',
  imports: [CommonModule ,FormsModule],
  templateUrl: './sanpham.component.html',
  styleUrls: ['./sanpham.component.css']
})
export class SanphamComponent implements OnInit {
  newSanPham: any = {};
  sanPhams: any[] = [];
  sanPhamChiTiets: any[] = [];
  thuongHieus: any[] = [];
  chatLieus: any[] = [];
  coAos: any[] = [];
  xuatXus: any[] = [];
  showModal: boolean = false;
  showModalSanPhamChiTiet: boolean = false;  // Trạng thái để kiểm tra modal hiển thị hay không
  sanPhamRequest: any = {
    ma: '',
    ten: '',
    thuongHieu: { id: '' },
    xuatXu: { id: '' },
    chatLieu: { id: '' },
    coAo: { id: '' },
    trangThai: 0,
    ngayTao: '',
    ngaySua: ''
  };
  constructor(private sanphamService: SanphamService) {}

  ngOnInit(): void {
    this.loadData()
  }

  // hàm load 
  loadData() : void{
  this.listSanPham()
  this.listSanPhamChiTiet()
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

  listSanPhamChiTiet(): void{
    this.sanphamService.getSanPhamChiTiet().subscribe(dataSanPhamChiTiet => {
      console.log("data", dataSanPhamChiTiet);
      this.sanPhamChiTiets = Array.isArray(dataSanPhamChiTiet.content) ? dataSanPhamChiTiet.content : [];
    })
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
  // Hàm mở modal sản phẩm
  openModalSanPham() {
    this.showModal = true;
  }

  // Hàm đóng modal sản phẩm
  closeModalSanPham() {
    this.showModal = false;
  }

  // Hàm mở modal sản phẩm chi tiết
  openModalSanPhamChiTiet(idSanPham: number): void {
    this.sanphamService.getSanPhamChiTiet().subscribe(dataSanPhamChiTiet => {
      this.sanPhamChiTiets = Array.isArray(dataSanPhamChiTiet.content)
        ? dataSanPhamChiTiet.content.filter(item => item.idSanPham === idSanPham)
        : [];
      console.log("San phẩm chi tiết:", this.sanPhamChiTiets);
      this.showModalSanPhamChiTiet = true;
    });
  }    
    
    // Hàm đóng modal sản phẩm chi tiết
    closeModalSanPhamChiTiet() {
      this.showModalSanPhamChiTiet = false;
    }

    // thêm sản phẩm
    addSanPham(): void {
      this.sanPhamRequest.ngayTao = new Date().toISOString().split('T')[0];
      this.sanPhamRequest.ngaySua = new Date().toISOString().split('T')[0]; 
      this.sanPhamRequest.trangThai = 0
      console.log("SanPham Request:", this.sanPhamRequest);
    
      this.sanphamService.addSanPham(this.sanPhamRequest).subscribe({
        next: (response) => {
          console.log("Thêm sản phẩm thành công:", response);
          alert("Thêm sản phẩm thành công");
          this.closeModalSanPham();
          this.listSanPham(); // Reload danh sách sản phẩm
        },
        error: (error) => {
          console.error("Lỗi khi thêm sản phẩm:", error);
          alert("Thêm sản phẩm thất bại");
        }
      });
    } 
}
