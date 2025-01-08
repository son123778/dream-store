import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { importProvidersFrom } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http'; // Import HttpClientModule
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { routes } from './app.routes';
 // Import routes từ app.routes.ts

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }), // Tối ưu hóa Zone.js
    provideRouter(routes), // Cấu hình routes từ app.routes.ts
    importProvidersFrom(
      CommonModule,
      BrowserAnimationsModule, // Hỗ trợ Animation
      HttpClientModule, // Để gọi API
      RouterModule, // Định nghĩa Router
      MatSidenavModule, // Sidenav Material
      MatToolbarModule, // Toolbar Material
      MatButtonModule, // Button Material
      MatIconModule // Icon Material
    )
  ]
};
