import { Routes } from '@angular/router';
import { SignupComponent } from './pages/signup/signup.component';
import { SuccessComponent as SignupSuccessComponent } from './pages/signup/success/success.component';

export const routes: Routes = [
  {
    path: 'signup',
    component: SignupComponent,
  },
  {
    path: 'signup/success',
    component: SignupSuccessComponent,
  },
];
