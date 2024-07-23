import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { PrescripcionService } from '../../services/prescripcion.service';
import { Paciente, NuevaPrescripcion, ProblemDetails } from '../../../generated/openapi';
// @ts-ignore
import { DateTime } from 'luxon';

interface PrescripcionTabla extends NuevaPrescripcion {
  fechaInicio: string | null;
  horaInicio: string;
}

@Component({
  selector: 'app-datos-paciente',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    MatButtonModule,
    MatTableModule,
    MatSnackBarModule,
    MatFormFieldModule,
    MatInputModule
  ],
  templateUrl: './datosPaciente.html',
  styleUrls: ['./datosPaciente.component.scss'],
})
export class DatosPacienteComponent implements OnInit {
  @Input() paciente: Paciente = {
    id: 1,
    nombre: 'Alan',
    apellidos: 'Turing',
    fechaDeNacimiento: '1912-06-23',
    estatura: 1.70,
    peso: '60',
    token: 'asdf'
  };

  success?: string;
  error?: string;
  dataSource = new MatTableDataSource<PrescripcionTabla>();
  displayedColumns: string[] = [
    'medicamento', 'frecuenciaDosis', 'indicaciones', 'duracion',
    'numeroDeDosis', 'cantidadPorDosis', 'fechaInicio', 'horaInicio', 'figura'
  ];

  constructor(
    private router: Router,
    private prescripcionService: PrescripcionService,
    private snackBar: MatSnackBar
  ) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras?.state?.['paciente']) {
      this.paciente = navigation.extras.state['paciente'];
    }
  }

  ngOnInit() {
    this.cargarPrescripciones();
  }

  private async cargarPrescripciones() {
    if (!this.paciente) return;

    this.error = undefined;
    this.success = undefined;

    try {
      const prescripciones = await this.prescripcionService.obtenerPrescripciones(1);
      this.dataSource.data = prescripciones.map((p: { inicio: string }) => {
        console.log("Valor original de inicio:", p.inicio);
        const fechaInicio = p.inicio ? DateTime.fromISO(p.inicio, { zone: 'utc' }) : null;
        console.log("Valor convertido de fechaInicio:", fechaInicio?.toString());
        return {
          ...p,
          fechaInicio: fechaInicio ? fechaInicio.toFormat('dd/MM/yyyy', { zone: 'utc' }) : null,
          horaInicio: fechaInicio ? fechaInicio.toFormat('HH:mm', { zone: 'utc' }) : ''
        };
      });
      this.snackBar.open('Prescripciones cargadas con éxito', 'Cerrar', { duration: 3000 });
      this.success = 'Prescripciones cargadas con éxito';
    } catch (e) {
      const errorResponse = e as HttpErrorResponse;
      const problemDetails = errorResponse.error as ProblemDetails;
      this.error = problemDetails.detail;
      this.snackBar.open(problemDetails.detail, 'Cerrar', { duration: 3000 });
    }
  }

  public async onSubmit() {
    if (!this.paciente) return;

    this.error = undefined;
    this.router.navigate(['/agregar-prescripcion'], { state: { paciente: this.paciente } });
  }
}



