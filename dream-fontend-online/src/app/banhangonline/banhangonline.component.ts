import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-banhangonline',
  standalone: true,
 
  templateUrl: './banhangonline.component.html',
  styleUrl: './banhangonline.component.css'
})
export class BanhangonlineComponent {
  
  constructor(private router: Router) {}

  
  chuyenDenHoaDon() {
    
    this.router.navigate(['/hoadon']);
  }


}
