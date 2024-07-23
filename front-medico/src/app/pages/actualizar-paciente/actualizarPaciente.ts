import { Component, Input, signal, effect, WritableSignal } from '@angular/core';
import {
  FormControl,
  Validators,
  FormsModule,
  ReactiveFormsModule,
  FormGroup,
} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { PacienteService } from "../../services/paciente.service";
import {ProblemDetails, Paciente} from '../../../generated/openapi';
import { CommonModule } from '@angular/common';
import { MatTableModule } from "@angular/material/table";
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {HttpErrorResponse} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";

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
    nombre: new FormControl<string>('', Validators.required),
    apellidoPaterno: new FormControl<string>('', Validators.required),
    apellidoMaterno: new FormControl<string>('', Validators.required),
    fechaNacimiento: new FormControl<Date | null>(null, Validators.required),
    estatura: new FormControl<number | null>(null, Validators.required),
    peso: new FormControl<string>('', Validators.required),
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

    this.error.set(undefined);
    this.success.set(undefined);



    try {
      console.info('Modificando paciente: ', this.paciente)
      // @ts-ignore
      await this.pacienteService.actualizarPaciente(this.paciente.id, paciente);
      this.snackBar.open('Actualización éxitosa', 'Cerrar', { duration: 3000 });
      this.success.set('Actualización éxitosa');
      this.router.navigate(['/datos-paciente'], { state: { paciente: this.paciente } });
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
