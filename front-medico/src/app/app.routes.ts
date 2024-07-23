import { Routes } from '@angular/router';
import { SignupComponent } from './pages/signup/signup.component';
import { SuccessComponent as SignupSuccessComponent } from './pages/signup/success/success.component';
import { AgregarPrescripcionComponent } from './pages/agregar-prescripcion/agregarPrescripcion';
import { DatosPacienteComponent } from './pages/datos-paciente/datosPaciente';
import {ActualizarPacienteComponent} from "./pages/actualizar-paciente/actualizarPaciente";

export const routes: Routes = [
  {
    path: 'signup',
    component: SignupComponent,
  },
  {
    path: 'signup/success',
    component: SignupSuccessComponent,
  },
  {
    path: 'agregar-prescripcion',
    component: AgregarPrescripcionComponent,
  },
  {
    path: 'datos-paciente',
    component: DatosPacienteComponent,
  },
  {
    path: 'actualizar-paciente',
    component: ActualizarPacienteComponent,
  },
];
