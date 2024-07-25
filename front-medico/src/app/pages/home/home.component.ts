import { Component, effect } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { Router, RouterModule } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatButtonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  constructor(usuarioService: UsuarioService, router: Router) {
    effect(() => {
      const usuario = usuarioService.usuario()
      if (usuario == null) return

      if (usuario.rol == "doctor")
        router.navigate(["/pacientes"], {replaceUrl: true})
      else
        router.navigate(["/qr-show"], {replaceUrl: true})
    })
  }
}
