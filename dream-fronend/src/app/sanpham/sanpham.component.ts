import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SanphamService } from './sanpham.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-sanpham',
  standalone: true,
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
  sizes: any[] = [];
  mauSacs: any[] = [];
  showModal: boolean = false;
  showModalSanPhamChiTiet: boolean = false;
  showModalSanPhamChiTietThem: boolean = false;   // Trạng thái để kiểm tra modal hiển thị hay không

  searchFilter: any = {
    thuongHieu: { id: '' },
    xuatXu: { id: '' },
    chatLieu: { id: '' },
    coAo: { id: '' },
    trangThai: ''
  };
  
  sanPhamRequest: any = {
    ma: '',
    ten: '',
    thuongHieu: { id: '' },
    xuatXu: { id: '' },
    chatLieu: { id: '' },
    coAo: { id: '' },
    trangThai: 1,
    ngayTao: '',
    ngaySua: ''
  };

  sanPhamChiTietRequest: any = {
  ma: '',             
  gia: '',            
  soLuong: '',         
  sanPham: {          
    id: '',         
    ten: ''          
  },
  size: {            
    id: ''           
  },
  mauSac: {            
    id: ''           
  },
  trangThai: 1,        
  ngayTao: '',         
  ngaySua: ''          
};


  editSanPham(sanPham: any): void {
    this.sanPhamRequest = { 
      id: sanPham.id, // Thêm ID vào đây
      ma: sanPham.ma,
      ten: sanPham.ten,
      thuongHieu: { id: sanPham.idThuongHieu },
      xuatXu: { id: sanPham.idXuatXu },
      chatLieu: { id: sanPham.idChatLieu },
      coAo: { id: sanPham.idCoAo },
      trangThai: sanPham.trangThai,
      ngayTao: sanPham.ngayTao,
      ngaySua: new Date().toISOString().split('T')[0], // Ngày sửa là ngày hiện tại
    };
    this.showModal = true; // Mở modal
    
}
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
  this.listSize()
  this.listMauSac()
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
  
  listSize(): void {
    this.sanphamService.getSize().subscribe(data => {
      console.log("Size Data", data);
      this.sizes = Array.isArray(data) ? data : []; 
    });
  } 

  listMauSac(): void {
    this.sanphamService.getMauSac().subscribe(data => {
      console.log("Màu sắc Data", data);
      this.mauSacs = Array.isArray(data) ? data : []; 
    });
  } 
  // Hàm mở modal sản phẩm
  openModalSanPham() {
    this.sanPhamRequest = {
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
    this.showModal = true;
  }

  openModalSanPhamChiTietThem(): void {
    // Reset model cho sản phẩm chi tiết
    this.sanPhamChiTietRequest = {
      ma: '',             
      gia: '',            
      soLuong: '',         
      sanPham: {          
        id: '',         
        ten: ''          
      },
      size: {            
        id: ''           
      },
      mauSac: {            
        id: ''           
      },
      trangThai: 1,        
      ngayTao: '',         
      ngaySua: ''          
    };
  
    // Tự động điền tên sản phẩm từ sản phẩm đã chọn
    if (this.selectedProduct) {
      this.sanPhamRequest.ten = this.selectedProduct.ten;
    }
    
    this.showModalSanPhamChiTietThem = true;
  }

  closeModalSanPhamChiTietThem(){
    this.showModalSanPhamChiTietThem = false;
  }

  // Hàm đóng modal sản phẩm
  closeModalSanPham() {
    this.showModal = false;
  }

  // Hàm mở modal sản phẩm chi tiết
  openModalSanPhamChiTiet(idSanPham: number): void {
    // Tìm sản phẩm được chọn từ danh sách sanPhams
    this.selectedProduct = this.sanPhams.find(product => product.id === idSanPham);
    
    // Lấy danh sách sản phẩm chi tiết của sản phẩm được chọn
    this.sanphamService.getSanPhamChiTiet().subscribe(dataSanPhamChiTiet => {
      this.sanPhamChiTiets = Array.isArray(dataSanPhamChiTiet.content)
        ? dataSanPhamChiTiet.content.filter(item => item.idSanPham === idSanPham)
        : [];
      console.log("Sản phẩm chi tiết:", this.sanPhamChiTiets);
      this.showModalSanPhamChiTiet = true;
    });
  } 
  
  selectedProduct: any = {};
    
    // Hàm đóng modal sản phẩm chi tiết
    closeModalSanPhamChiTiet() {
      this.showModalSanPhamChiTiet = false;
    }

    // thêm sản phẩm
    addSanPham(): void {
      this.sanPhamRequest.ngayTao = new Date().toISOString().split('T')[0];
      this.sanPhamRequest.ngaySua = new Date().toISOString().split('T')[0]; 
      this.sanPhamRequest.trangThai = 1
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
    // sửa sản phẩm
    updateSanPham(): void {
      this.sanPhamRequest.ngaySua = new Date().toISOString().split('T')[0]; // Ngày sửa hiện tại
    
      console.log("Update SanPham Request:", this.sanPhamRequest);
    
      this.sanphamService.updateSanPham(this.sanPhamRequest).subscribe({
        next: (response) => {
          console.log("Cập nhật sản phẩm thành công:", response);
          alert("Cập nhật sản phẩm thành công");
          this.closeModalSanPham();
          this.listSanPham(); // Reload danh sách sản phẩm
        },
        error: (error) => {
          console.error("Lỗi khi cập nhật sản phẩm:", error);
          alert("Cập nhật sản phẩm thất bại");
        }
      });
    }

    addSanPhamChiTiet(): void {
      // Gán ngày tạo, ngày sửa, trạng thái,...
      this.sanPhamRequest.ngayTao = new Date().toISOString().split('T')[0];
      this.sanPhamRequest.ngaySua = new Date().toISOString().split('T')[0];
      this.sanPhamRequest.trangThai = 1;
      
      console.log("Sản phẩm chi tiết request:", this.sanPhamRequest);
      
      this.sanphamService.addSanPhamChiTiet(this.sanPhamRequest).subscribe({
        next: (response) => {
          console.log("Thêm sản phẩm chi tiết thành công:", response);
          alert("Thêm sản phẩm chi tiết thành công");
          this.closeModalSanPhamChiTietThem();
          // Reload danh sách sản phẩm chi tiết nếu cần
          this.listSanPhamChiTiet();
        },
        error: (error) => {
          console.error("Lỗi khi thêm sản phẩm chi tiết:", error);
          alert("Thêm sản phẩm chi tiết thất bại");
        }
      });
    }
    
}
