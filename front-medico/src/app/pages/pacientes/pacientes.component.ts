import {
  Component,
  computed,
  effect,
  model,
  OnInit,
  Signal,
  signal,
} from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { Paciente } from '../../../generated/openapi';
import { UsuarioService } from '../../services/usuario.service';
import { PacienteService } from '../../services/paciente.service';
import { Router, RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-pacientes',
  standalone: true,
  imports: [
    MatCardModule,
    MatInputModule,
    MatFormFieldModule,
    RouterModule,
    MatButtonModule,
    MatIconModule,
    FormsModule,
  ],
  templateUrl: './pacientes.component.html',
  styleUrl: './pacientes.component.scss',
})
export class PacientesComponent implements OnInit {
  pacientes = signal<Paciente[]>([]);
  searchQuery = model('');
  pacientesFiltrados: Signal<Paciente[]>;

  constructor(
    private usuarioService: UsuarioService,
    private pacienteService: PacienteService,
    public router: Router
  ) {
    effect(
      async () => {
        this.fetchPacientes();
      },
      { allowSignalWrites: true }
    );

    this.pacientesFiltrados = computed(() => {
      const searchQuery = this.searchQuery().toLowerCase().trim();
      if (searchQuery == '') return this.pacientes();
      return this.pacientes().filter(
        (p) =>
          p.nombre?.toLowerCase().includes(searchQuery) ||
          p.apellidos?.toLowerCase().includes(searchQuery)
      );
    });
  }

  ngOnInit() {
    this.pacienteService.pacienteActual.set(undefined);
  }

  private async fetchPacientes() {
    const usuario = this.usuarioService.usuario();
    if (usuario == null) {
      this.pacientes.set([]);
      return;
    }
    const pacientes = await this.pacienteService.getPacientes(+usuario.id);
    this.pacientes.set(pacientes);
  }
}
