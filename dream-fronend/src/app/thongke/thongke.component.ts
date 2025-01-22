import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ThongKeRequest } from './thongke-request.model';
import { FormBuilder, FormGroup } from '@angular/forms';
import Chart from 'chart.js/auto';
import { ThongKeService, ThongKeResponse, ThongKeThangResponse } from './thongke.service';
import { ReactiveFormsModule } from '@angular/forms';
@Component({
  selector: 'app-thongke',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule, FormsModule],
  templateUrl: './thongke.component.html',
  styleUrls: ['./thongke.component.css'],
})
export class ThongkeComponent implements OnInit {
  thongKeData: ThongKeResponse | null = null;
  selectedType: string = 'nam-nay';
  chart: any;

  constructor(private thongKeService: ThongKeService) {}

  ngOnInit(): void {
    this.loadThongKe(this.selectedType);
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
        } else {
          this.loadBieuDoThang(); // Biểu đồ theo tháng
        }
      },
      (error) => {
        console.error('Lỗi khi lấy dữ liệu tổng quan:', error);
        this.thongKeData = { soHoaDon: 0, tongDoanhThu: 0, soKhachHang: 0 };
      }
    );
  }

  // Biểu đồ từng tháng
  loadBieuDoThang(): void {
    this.thongKeService.thongKeTungThangNam().subscribe(
      (data: ThongKeThangResponse[]) => {
        const labels = data.map((item) => `Tháng ${item.thang}`);
        const values = data.map((item) => item.tongDoanhThu);

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

  // Vẽ biểu đồ
  renderChart(labels: string[], data: number[], label: string): void {
    this.destroyChart();

    const ctx = document.getElementById('chart') as HTMLCanvasElement;
    this.chart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            label: label,
            data: data,
            borderColor: '#4caf50',
            borderWidth: 2,
            fill: false,
            tension: 0.1,
          },
        ],
      },
      options: {
        responsive: true,
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
}