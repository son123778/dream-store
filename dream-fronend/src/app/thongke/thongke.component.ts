import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import Chart from 'chart.js/auto';
import { ThongKeService, ThongKeResponse, ThongKeThangResponse, ThongKeThangNayResponse ,
  ThongKeHomNayResponse, TopSanPhamResponse} from './thongke.service';

@Component({
  selector: 'app-thongke',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './thongke.component.html',
  styleUrls: ['./thongke.component.css'],
})
export class ThongkeComponent implements OnInit {
  thongKeData: ThongKeResponse | null = null;
  selectedType: string = 'nam-nay';
  chart: any;
  pieChart: any;
  topSanPhamData: TopSanPhamResponse[] = [];
  page: number = 0;
  size: number = 3;

  constructor(private thongKeService: ThongKeService) {}

  ngOnInit(): void {
    this.loadThongKe(this.selectedType);
    this.loadTopSanPhamNamNay();
  }

  loadThongKe(type: string): void {
    this.selectedType = type;
    this.thongKeData = null;
  
    // Gọi API lấy dữ liệu tổng quan
    this.thongKeService.thongKeTongQuan(type).subscribe(
      (data) => {
        this.thongKeData = data || { soHoaDon: 0, tongDoanhThu: 0, soKhachHang: 0 };
  
        // Hiển thị biểu đồ
        if (type === 'tat-ca') {
          this.loadBieuDoNam(); // Biểu đồ theo năm
        } else if (type === 'nam-nay') {
          this.loadBieuDoThang(); // Biểu đồ theo năm
        } else if (type === 'thang-nay') {
          this.loadBieuDoNgayTrongThang(); // Biểu đồ doanh thu từng ngày trong tháng
        } else {
          this.loadBieuDoHomNay(); // Biểu đồ theo tháng
        }
      },
      (error) => {
        console.error('Lỗi khi lấy dữ liệu tổng quan:', error);
        this.thongKeData = { soHoaDon: 0, tongDoanhThu: 0, soKhachHang: 0 };
      }
    );
  }
  
  // Biểu đồ doanh thu từng ngày trong tháng
loadBieuDoNgayTrongThang(): void {
  this.thongKeService.thongKeTungNgayTrongThang().subscribe(
    (data: ThongKeThangNayResponse[]) => {
      const labels = data.map((item) => `Ngày ${item.ngay}`);
      const values = data.map((item) => item.tongDoanhThu);

      this.renderChart(labels, values, 'Doanh thu từng ngày trong tháng');
    },
    (error) => {
      console.error('Lỗi khi lấy dữ liệu doanh thu ngày trong tháng:', error);
    }
  );
}
  
// Biểu đồ doanh thu hôm nay
loadBieuDoHomNay(): void {
  this.thongKeService.thongKeHomNay().subscribe(
    (data: ThongKeHomNayResponse) => {
      const labels = ['Ngày hôm nay'];
      const values = [data.tongDoanhThu];

      this.renderChart(labels, values, 'Doanh thu hôm nay');
    },
    (error) => {
      console.error('Lỗi khi lấy dữ liệu doanh thu hôm nay:', error);
    }
  );
}
  // Biểu đồ từng tháng
  loadBieuDoThang(): void {
    this.thongKeService.thongKeTungThangNam().subscribe(
      (data: ThongKeThangResponse[]) => {
        const labels: string[] = [];
        const values: number[] = new Array(12).fill(0); // Mặc định 12 tháng có giá trị 0

        data.forEach((item) => {
          values[item.thang - 1] = item.tongDoanhThu; // Gán doanh thu vào đúng tháng
        });

        for (let i = 1; i <= 12; i++) {
          labels.push(`Tháng ${i}`);
        }

        this.renderChart(labels, values, 'Doanh thu từng tháng');
      },
      (error) => {
        console.error('Lỗi khi lấy dữ liệu từng tháng:', error);
      }
    );
  }

  // Biểu đồ theo năm
  loadBieuDoNam(): void {
    this.thongKeService.thongKeTungNam().subscribe(
      (data: ThongKeThangResponse[]) => {
        const labels = data.map((item) => `Năm ${item.thang}`);
        const values = data.map((item) => item.tongDoanhThu);

        this.renderChart(labels, values, 'Doanh thu từng năm');
      },
      (error) => {
        console.error('Lỗi khi lấy dữ liệu từng năm:', error);
      }
    );
  }

  // Vẽ biểu đồ cột
  renderChart(labels: string[], data: number[], label: string): void {
    this.destroyChart();

    const ctx = document.getElementById('chart') as HTMLCanvasElement;
    if (!ctx) {
      console.warn('Không tìm thấy phần tử canvas cho biểu đồ.');
      return;
    }

    this.chart = new Chart(ctx, {
      type: 'bar', // Thay đổi từ 'line' thành 'bar' để vẽ biểu đồ cột
      data: {
        labels: labels,
        datasets: [
          {
            label: label,
            data: data,
            backgroundColor: 'rgba(54, 162, 235, 0.6)', // Màu xanh dương
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1,
          },
        ],
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true, // Luôn bắt đầu từ 0
          },
        },
        plugins: {
          legend: {
            display: true,
          },
        },
      },
    });
  }

  // Xóa biểu đồ cũ trước khi vẽ mới
  destroyChart(): void {
    if (this.chart) {
      this.chart.destroy();
      this.chart = null;
    }
  }
   // Tải danh sách top sản phẩm bán chạy hôm nay
   loadTopSanPhamHomNay(): void {
    this.thongKeService.topSanPhamHomNay(this.page, this.size).subscribe(
      (data: TopSanPhamResponse[]) => {
        this.topSanPhamData = data;
        this.renderPieChart();
      },
      (error) => {
        console.error('Lỗi khi lấy danh sách top sản phẩm hôm nay:', error);
      }
    );
  }

  // Tải danh sách top sản phẩm bán chạy trong tháng này
  loadTopSanPhamThangNay(): void {
    this.thongKeService.topSanPhamThangNay(this.page, this.size).subscribe(
      (data: TopSanPhamResponse[]) => {
        this.topSanPhamData = data;
        this.renderPieChart();
      },
      (error) => {
        console.error('Lỗi khi lấy danh sách top sản phẩm tháng này:', error);
      }
    );
  }

  // Tải danh sách top sản phẩm bán chạy trong năm nay
  loadTopSanPhamNamNay(): void {
    this.thongKeService.topSanPhamNamNay(this.page, this.size).subscribe(
      (data: TopSanPhamResponse[]) => {
        this.topSanPhamData = data;
        this.renderPieChart();
      },
      (error) => {
        console.error('Lỗi khi lấy danh sách top sản phẩm năm nay:', error);
      }
    );
  }

  // Tải danh sách top sản phẩm bán chạy tất cả thời gian
  loadTopSanPhamTatCa(): void {
    this.thongKeService.topSanPhamTatCa(this.page, this.size).subscribe(
      (data: TopSanPhamResponse[]) => {
        this.topSanPhamData = data;
        this.renderPieChart();
      },
      (error) => {
        console.error('Lỗi khi lấy danh sách top sản phẩm tất cả thời gian:', error);
      }
    );
  }
   // Vẽ biểu đồ tròn cho top sản phẩm bán chạy
   renderPieChart(): void {
    const topProducts = this.topSanPhamData.slice(0, 3); // Lấy 3 sản phẩm đầu tiên
    const otherProducts = this.topSanPhamData.slice(3); // Các sản phẩm còn lại

    const labels = topProducts.map(product => product.tenSanPham);
    const data = topProducts.map(product => product.tongSoLuong);

    // Thêm phần "Các sản phẩm khác"
    if (otherProducts.length > 0) {
      labels.push('Các sản phẩm khác');
      data.push(otherProducts.reduce((sum, product) => sum + product.tongSoLuong, 0));
    }

    // Vẽ biểu đồ tròn
    const ctx = document.getElementById('pieChart') as HTMLCanvasElement;
    if (!ctx) {
      console.warn('Không tìm thấy phần tử canvas cho biểu đồ tròn.');
      return;
    }

    if (this.pieChart) {
      this.pieChart.destroy(); // Xóa biểu đồ cũ trước khi vẽ mới
    }

    this.pieChart = new Chart(ctx, {
      type: 'pie',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Top sản phẩm bán chạy',
            data: data,
            backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#B0B0B0'],
            borderColor: ['#FF6384', '#36A2EB', '#FFCE56', '#B0B0B0'],
            borderWidth: 1,
          },
        ],
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'top',
          },
          tooltip: {
            callbacks: {
              label: (tooltipItem) => {
                const label = tooltipItem.label || '';
                const value = tooltipItem.raw;
                return `${label}: ${value} sản phẩm`;
              },
            },
          },
        },
      },
    });
  }

}
