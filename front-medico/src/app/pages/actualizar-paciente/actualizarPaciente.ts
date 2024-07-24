import { Component, Input, signal, WritableSignal } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { PacienteService } from "../../services/paciente.service";
import { ProblemDetails, Paciente } from '../../../generated/openapi';
import { CommonModule } from '@angular/common';
import { MatTableModule } from "@angular/material/table";
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { HttpErrorResponse } from "@angular/common/http";
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
  selector: 'app-actualizarPaciente',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatCardModule,
    MatRadioModule,
    MatButtonModule,
    MatTableModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  templateUrl: './actualizarPaciente.html',
  styleUrls: ['./actualizarPaciente.component.scss'],
})
export class ActualizarPacienteComponent {
  @Input() paciente: Paciente | undefined;

  pacienteForm: FormGroup = new FormGroup({
    nombre: new FormControl<string>(''),
    apellidoPaterno: new FormControl<string>(''),
    apellidoMaterno: new FormControl<string>(''),
    fechaNacimiento: new FormControl<Date | null>(null),
    estatura: new FormControl<number | null>(null),
    peso: new FormControl<string>(''),
  });

  error = signal<string | undefined>(undefined);
  success = signal<string | undefined>(undefined);

  constructor(
    private router: Router,
    private pacienteService: PacienteService,
    private snackBar: MatSnackBar
  ) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras?.state?.['paciente']) {
      this.paciente = navigation.extras.state['paciente'];
    }
  }

  async onSubmit() {
    if (!this.paciente) return;

    this.error.set(undefined);
    this.success.set(undefined);

    const nombre = this.pacienteForm.value.nombre || this.paciente.nombre;

    const apellidoPaterno = this.pacienteForm.value.apellidoPaterno || this.paciente.apellidos?.split(' ')[0] || '';
    const apellidoMaterno = this.pacienteForm.value.apellidoMaterno || this.paciente.apellidos?.split(' ')[1] || '';

    const fechaDeNacimiento = this.pacienteForm.value.fechaNacimiento
      ? this.pacienteForm.value.fechaNacimiento.toISOString().split('T')[0]
      : this.paciente.fechaDeNacimiento;

    const estatura = this.pacienteForm.value.estatura !== null ? this.pacienteForm.value.estatura : this.paciente.estatura;
    const peso = this.pacienteForm.value.peso || this.paciente.peso;

    const apellidos = [apellidoPaterno, apellidoMaterno].filter(apellido => apellido).join(' ');

    const updatedPaciente: Paciente = {
      ...this.paciente,
      nombre,
      apellidos,
      fechaDeNacimiento,
      estatura,
      peso,
    };

    try {
      await this.pacienteService.actualizarPaciente(this.paciente.id, updatedPaciente);
      this.snackBar.open('Actualización éxitosa', 'Cerrar', { duration: 3000 });
      this.success.set('Actualización éxitosa');
      this.router.navigate(['/datos-paciente'], { state: { paciente: updatedPaciente } });
    } catch (e) {
      const errorResponse = e as HttpErrorResponse;
      const problemDetails = errorResponse.error as ProblemDetails;
      this.error.set(problemDetails.detail);
      this.snackBar.open(problemDetails.detail, 'Cerrar', { duration: 3000 });
    }
  }

  async cerrarComponente() {
    this.router.navigate(['/datos-paciente'], { state: { paciente: this.paciente } });
  }
}
