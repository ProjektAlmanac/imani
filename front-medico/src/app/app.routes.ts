import { Routes } from '@angular/router';

import { AgregarPrescripcionComponent } from './pages/agregar-prescripcion/agregarPrescripcion';
import { DatosPacienteComponent } from './pages/datos-paciente/datosPaciente';

export const routes: Routes = [
  {
    path: 'agregar-prescripcion',
    component: AgregarPrescripcionComponent,
  },
  {
    path: 'datos-paciente',
    component: DatosPacienteComponent,
  },
];
