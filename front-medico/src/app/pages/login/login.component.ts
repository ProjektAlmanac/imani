import { Component, signal } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Login, ProblemDetails } from '../../../generated/openapi';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  public formLogin: FormGroup;

  error = signal<string | undefined>(undefined);

  constructor(
    private loginService: LoginService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.formLogin = this.formBuilder.group({
      nombreUsuario: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  public async login() {
    if (this.formLogin.invalid) {
      return;
    }
    this.error.set(undefined);
    const usuario = this.formLogin.getRawValue();
    const login: Login = {
      usuario: usuario.nombreUsuario,
      password: usuario.password,
    };
    try {
      const usuario = await this.loginService.iniciarSesion(login);
      if (usuario.rol === 'doctor') {
        this.router.navigate(['pacientes']);
      } else {
        this.router.navigate(['']);
      }
    } catch (e) {
      const errorResponse = e as HttpErrorResponse;
      const problemDetails = errorResponse.error as ProblemDetails;
      this.error.set(problemDetails.detail);
    }
  }
}
