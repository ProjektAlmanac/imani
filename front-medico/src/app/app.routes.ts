import { Routes } from '@angular/router';
import { GenerateQrComponent } from './pages/generate-qr/generate-qr.component';
import { SignupComponent } from './pages/signup/signup.component';
import { SuccessComponent as SignupSuccessComponent } from './pages/signup/success/success.component';

export const routes: Routes = [
  {
    path: 'qr-show',
    component: GenerateQrComponent,
  },
  {
    path: 'signup',
    component: SignupComponent,
  },
  {
    path: 'signup/success',
    component: SignupSuccessComponent,
  },
];
