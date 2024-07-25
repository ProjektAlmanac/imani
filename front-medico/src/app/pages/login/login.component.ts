import {Component} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Login} from "../../../generated/openapi";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormField,
    MatInput,
    MatButton
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  public formLogin: FormGroup;

  constructor(
    private loginService:LoginService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.formLogin = this.formBuilder.group({
      nombreUsuario: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  public login() {
    if(this.formLogin.invalid){
      return;
    }
    const usuario = this.formLogin.getRawValue();
    const login: Login = {usuario: usuario.nombreUsuario, password: usuario.password};
    this.loginService.logea(login)
      .then((response) => {
        localStorage.setItem('usuario', JSON.stringify(response));
        this.router.navigate(['agregar-prescripcion']);
      })
      .catch((error) => {
        console.error(error);}
      );
  }

}
