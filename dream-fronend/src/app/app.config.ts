import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { importProvidersFrom } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list'; // Thêm module cho danh sách
import { MatTableModule } from '@angular/material/table'; // Thêm module cho bảng
import { MatInputModule } from '@angular/material/input'; // Thêm module cho input
import { MatFormFieldModule } from '@angular/material/form-field'; // Thêm module cho form
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Thêm ReactiveFormsModule
import { MatPaginatorModule } from '@angular/material/paginator'; // Thêm module phân trang
import { MatCardModule } from '@angular/material/card'; // Thêm module card
import { MatSortModule } from '@angular/material/sort'; // Thêm module sắp xếp
import { MatSelectModule } from '@angular/material/select'; // Thêm module select
import { MatCheckboxModule } from '@angular/material/checkbox'; // Thêm module checkbox
import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    importProvidersFrom(
      CommonModule,
      BrowserAnimationsModule,
      RouterModule,
      MatSidenavModule,
      MatToolbarModule,
      MatButtonModule,
      MatIconModule,
      MatListModule,
      MatTableModule,
      MatInputModule,
      MatFormFieldModule,
      MatPaginatorModule,
      MatCardModule,
      MatSortModule,
      MatSelectModule,
      MatCheckboxModule,
      FormsModule,
      ReactiveFormsModule,
      HttpClientModule
    ),
  ],
};
