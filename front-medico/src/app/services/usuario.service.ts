import { Injectable, Signal, signal, WritableSignal } from '@angular/core';
import { Usuario } from '../../generated/openapi';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private readonly _usuario: WritableSignal<Usuario | null>;
  public readonly usuario: Signal<Usuario | null>;

  private static readonly USUARIO_KEY = 'usuario';

  constructor() {
    const usuarioSerializado = localStorage.getItem(UsuarioService.USUARIO_KEY);
    const usuario = JSON.parse(usuarioSerializado!);
    this._usuario = signal(usuario);
    this.usuario = this._usuario;
  }

  public setUsuario(usuario: Usuario | null) {
    if (usuario == null) {
      localStorage.removeItem(UsuarioService.USUARIO_KEY);
    } else {
      const usuarioSerializado = JSON.stringify(usuario);
      localStorage.setItem(UsuarioService.USUARIO_KEY, usuarioSerializado);
    }
    this._usuario.set(usuario);
  }
  public getUsuario(): Usuario | null {
    const usuarioSerializado = localStorage.getItem(UsuarioService.USUARIO_KEY);
    if (usuarioSerializado == null) {
      return null;
    } else {
      return JSON.parse(usuarioSerializado);
    }
  }
}
