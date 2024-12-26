import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

// Khởi chạy ứng dụng với cấu hình `appConfig`.
bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));
