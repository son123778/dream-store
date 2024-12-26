import { Component } from '@angular/core';

@Component({
  selector: 'app-banhang',
  imports: [],
  templateUrl: './banhang.component.html',
  styleUrl: './banhang.component.css'
})
export class BanhangComponent {
  public chartOptions = {
    responsive: true,
  };

  public chartLabels = ['January', 'February', 'March', 'April'];
  public chartData = [
    { data: [65, 59, 80, 81], label: 'Sales' },
  ];
}
