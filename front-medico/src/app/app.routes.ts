import { Routes } from '@angular/router';
import { GenerateQrComponent } from './pages/generate-qr/generate-qr.component';
import { SignupComponent } from './pages/signup/signup.component';
import { SuccessComponent as SignupSuccessComponent } from './pages/signup/success/success.component';
import { AgregarPrescripcionComponent } from './pages/agregar-prescripcion/agregarPrescripcion';
import { DatosPacienteComponent } from './pages/datos-paciente/datosPaciente';
import { ActualizarPacienteComponent } from './pages/actualizar-paciente/actualizarPaciente';
import { LoginComponent } from './pages/login/login.component';
import { PacientesComponent } from './pages/pacientes/pacientes.component';

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
  {
    path: 'pacientes/:id/agregar-prescripcion',
    component: AgregarPrescripcionComponent,
  },
  {
    path: 'pacientes/:id',
    component: DatosPacienteComponent,
  },
  {
    path: 'pacientes/:id/actualizar',
    component: ActualizarPacienteComponent,
  },
  {
    path: 'pacientes',
    component: PacientesComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
];
