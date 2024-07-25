import { Injectable } from '@angular/core';
import {
  Login,
  LoginService as LoginApiService,
} from '../../generated/openapi';
import { lastValueFrom } from 'rxjs';
import { UsuarioService } from './usuario.service';
@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(
    private loginService: LoginApiService,
    private usuarioService: UsuarioService
  ) {}

  public async iniciarSesion(login: Login) {
    const usuario = await lastValueFrom(this.loginService.login(login));
    this.usuarioService.setUsuario(usuario);
    return usuario;
  }
}
