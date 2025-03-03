import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: true, 
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'] 
})
export class HeaderComponent {
  modalCard: boolean = false;

  cardModal(event: Event): void {
    event.preventDefault(); // Ngăn chặn điều hướng mặc định
    event.stopPropagation(); // Ngăn chặn sự kiện lan truyền
    this.modalCard = !this.modalCard;
  }  
}
