import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TrangChuService } from './trangchu.service';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-trangchu',
  imports: [CommonModule, FormsModule],
  templateUrl: './trangchu.component.html',
  styleUrl: './trangchu.component.css',
})
export class TrangchuComponent implements OnInit {
constructor(private trangChuService: TrangChuService) { }
  ngOnInit(): void {
    

  }
}