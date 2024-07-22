import { Component, signal } from '@angular/core';
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
import { DoctorService } from '../../services/doctor.service';
import { FarmaceuticoService } from '../../services/farmaceutico.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ProblemDetails } from '../../../generated/openapi';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatCardModule,
    MatRadioModule,
    MatButtonModule,
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss',
})
export class SignupComponent {
  signupForm = new FormGroup({
    nombre: new FormControl('', Validators.required),
    apellidos: new FormControl('', Validators.required),
    nombreUsuario: new FormControl('', Validators.required),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(8),
    ]),
    rol: new FormControl('', Validators.required),
    centroMedico: new FormControl('', Validators.required),
  });

  error = signal<string | undefined>(undefined);

  constructor(
    private doctorService: DoctorService,
    private farmaceuticoService: FarmaceuticoService,
    private router: Router
  ) {
    this.signupForm.get('rol')?.valueChanges.subscribe((value) => {
      if (value === 'medico') {
        this.signupForm.controls.centroMedico.setValidators([
          Validators.required,
        ]);
      } else {
        this.signupForm.controls.centroMedico.clearValidators();
      }
      this.signupForm.controls.centroMedico.updateValueAndValidity();
    });
  }

  public async onSubmit() {
    if (!this.signupForm.valid) return;

    this.error.set(undefined);

    let promise: Promise<unknown>;

    if (this.signupForm.value.rol === 'medico') {
      promise = this.doctorService.crearDoctor({
        nombre: this.signupForm.value.nombre!,
        apellidos: this.signupForm.value.apellidos!,
        nombreUsuario: this.signupForm.value.nombreUsuario!,
        password: this.signupForm.value.password!,
        centroMedico: this.signupForm.value.centroMedico!,
      });
    } else {
      promise = this.farmaceuticoService.crearFarmaceutico({
        nombre: this.signupForm.value.nombre!,
        apellidos: this.signupForm.value.apellidos!,
        nombreUsuario: this.signupForm.value.nombreUsuario!,
        password: this.signupForm.value.password!,
      });
    }

    try {
      await promise;
      this.router.navigate(['/signup/success']);
    } catch (e) {
      const errorResponse = e as HttpErrorResponse;
      const problemDetails = errorResponse.error as ProblemDetails;
      this.error.set(problemDetails.detail);
    }
  }
}
