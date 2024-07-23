import { Routes } from '@angular/router';
import {SigupPatientComponent} from "./pages/sigup-patient/sigup-patient.component";

export const routes: Routes = [
  {
    path: 'home',
    loadComponent: () => import('./pages/sigup-patient/sigup-patient.component').then((m) => m.SigupPatientComponent),
  },
  {
    path: 'message/:id',
    loadComponent: () =>
      import('./view-message/view-message.page').then((m) => m.ViewMessagePage),
  },
  {
    path: 'signup-patient',
    component: SigupPatientComponent
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
];

