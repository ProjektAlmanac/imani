import {
  Component,
  computed,
  effect,
  Input,
  OnInit,
  signal,
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { PrescripcionService } from '../../services/prescripcion.service';
import { PacienteService } from '../../services/paciente.service';
import {
  Paciente,
  NuevaPrescripcion,
  ProblemDetails,
  Doctor,
  Farmaceutico,
  Usuario,
  Prescripcion,
} from '../../../generated/openapi';
// @ts-ignore
import { DateTime } from 'luxon';
import { UsuarioService } from '../../services/usuario.service';

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
    MatInputModule,
  ],
  templateUrl: './datosPaciente.html',
  styleUrls: ['./datosPaciente.component.scss'],
})
export class DatosPacienteComponent {
  paciente = this.pacienteService.pacienteActual;

  success = signal<string | undefined>(undefined);
  error = signal<string | undefined>(undefined);
  dataSource = new MatTableDataSource<PrescripcionTabla>();
  doctorColumns: string[] = [
    'medicamento',
    'frecuenciaDosis',
    'indicaciones',
    'duracion',
    'cantidadPorDosis',
    'fechaInicio',
    'horaInicio',
    'figura',
  ];
  farmaceuticoColumns: string[] = [
    'medicamento',
    'frecuenciaDosis',
    'indicaciones',
    'duracion',
    'cantidadPorDosis',
    'fechaInicio',
    'horaInicio',
    'figura',
    'numeroDeDosis',
  ];

  displayedColumns = signal<string[]>([]);

  /** Relaciona el id de una prescripción con su nueva cantidad */
  cambios = new Map<number, Prescripcion>();

  constructor(
    private router: Router,
    private prescripcionService: PrescripcionService,
    private pacienteService: PacienteService,
    private snackBar: MatSnackBar,
    public usuarioService: UsuarioService,
    route: ActivatedRoute
  ) {
    effect(this.cargarPrescripciones, { allowSignalWrites: true });
    effect(
      () => {
        if (this.usuarioService.usuario()?.rol == 'doctor')
          this.displayedColumns.set(this.doctorColumns);
        else this.displayedColumns.set(this.farmaceuticoColumns);
      },
      { allowSignalWrites: true }
    );

    route.paramMap.subscribe(async (paramMap) => {
      this.success.set(undefined);
      this.error.set(undefined);
      const id = Number(paramMap.get('id'));
      try {
        const paciente = await this.pacienteService.obtenerPaciente(id);
        this.paciente.set(paciente);
      } catch (e) {
        const errorResponse = e as HttpErrorResponse;
        const problemDetails = errorResponse.error as ProblemDetails;
        this.error.set(problemDetails.detail);
        this.snackBar.open(problemDetails.detail, 'Cerrar', { duration: 3000 });
      }
    });

    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras?.state?.['usuario']) {
      this.paciente = navigation.extras.state['usuario'];
    }
  }

  // async ngOnInit() {
  //   await this.obtenerPAciente();
  //   if (this.paciente) {
  //     await this.cargarPrescripciones();
  //   } else {
  //     console.error('Error: Paciente no obtenido correctamente.');
  //   }
  // }

  private async obtenerPAciente() {
    //if (!this.usuario?.idPaciente) return;
    // this.error = undefined;
    // this.success = undefined;
    // try {
    //   this.paciente = await this.pacienteService.obtenerPaciente(
    //     this.usuario?.idPaciente || 1
    //   );
    //   if (this.paciente && this.paciente.fechaDeNacimiento) {
    //     const fechaNacimiento = DateTime.fromFormat(
    //       this.paciente.fechaDeNacimiento,
    //       'dd-MM-yyyy'
    //     );
    //     if (fechaNacimiento.isValid) {
    //       this.paciente.fechaDeNacimiento = fechaNacimiento.toISODate();
    //     } else {
    //       console.error(
    //         'Fecha de nacimiento no válida:',
    //         this.paciente.fechaDeNacimiento
    //       );
    //     }
    //   }
    //   //this.snackBar.open('Paciente cargado con éxito', 'Cerrar', { duration: 3000 });
    //   //this.success = 'Paciente cargado con éxito';
    // } catch (e) {
    //   const errorResponse = e as HttpErrorResponse;
    //   const problemDetails = errorResponse.error as ProblemDetails;
    //   this.error = problemDetails.detail;
    //   this.snackBar.open(problemDetails.detail, 'Cerrar', { duration: 3000 });
    // }
  }

  private cargarPrescripciones = async () => {
    if (!this.paciente) return;

    this.error.set(undefined);
    this.success.set(undefined);

    try {
      console.log(this.paciente());
      const prescripciones =
        await this.prescripcionService.obtenerPrescripciones(
          this.paciente()?.id || 1
        );
      this.dataSource.data = prescripciones.map((p: { inicio: string }) => {
        const fechaInicio = p.inicio
          ? DateTime.fromISO(p.inicio, { zone: 'utc' })
          : null;
        return {
          ...p,
          fechaInicio: fechaInicio
            ? fechaInicio.toFormat('dd/MM/yyyy', { zone: 'utc' })
            : null,
          horaInicio: fechaInicio
            ? fechaInicio.toFormat('HH:mm', { zone: 'utc' })
            : '',
        };
      });
      //this.snackBar.open('Prescripciones cargadas con éxito', 'Cerrar', { duration: 3000 });
      //this.success = 'Prescripciones cargadas con éxito';
    } catch (e) {
      const errorResponse = e as HttpErrorResponse;
      const problemDetails = errorResponse.error as ProblemDetails;
      this.error.set(problemDetails.detail);
      this.snackBar.open(problemDetails.detail, 'Cerrar', { duration: 3000 });
    }
  };

  public async onSubmit() {
    //if (!this.usuario?.idPaciente) return;

    this.error.set(undefined);
    this.router.navigate(
      ['/pacientes', this.paciente()?.id, 'agregar-prescripcion'],
      {
        state: { paciente: this.paciente() },
      }
    );
  }

  // Método para verificar el tipo de usuario y acceder a propiedades específicas
  public esDoctor(usuario: any): usuario is Doctor {
    //return (usuario as Doctor).centroMedico !== undefined;
    return true;
  }

  formatTime(seconds: number): string {
    if (seconds < 60) {
      return `${seconds} segundos`;
    } else if (seconds < 3600) {
      const minutes = Math.floor(seconds / 60);
      return `${minutes} minutos`;
    } else if (seconds < 86400) {
      const hours = Math.floor(seconds / 3600);
      return `${hours} horas`;
    } else {
      const days = Math.floor(seconds / 86400);
      return `${days} días`;
    }
  }

  public async actualizarPaciente() {
    if (!this.paciente()) return;

    this.error.set(undefined);
    this.router.navigate(['/pacientes', this.paciente()?.id, 'actualizar'], {
      state: { paciente: this.paciente() },
    });
  }

  public guardarCambioCantidad(prescripcion: Prescripcion, e: Event) {
    const target = e.target as HTMLInputElement;
    const value = +target.value;
    prescripcion.numeroDeDosis = value;
    this.cambios.set(prescripcion.id, prescripcion);
  }

  public async subirCambios() {
    this.success.set(undefined)
    this.error.set(undefined)
    const pacienteId = this.paciente()?.id;
    if (pacienteId == undefined) return;
    const promises = [...this.cambios.entries()].map(([id, p]) =>
      this.prescripcionService.actualizarprescripcion(pacienteId, id, p)
    );
    try {
      await Promise.all(promises)
      this.snackBar.open('Prescripciones actualizadas con éxito', 'Cerrar', { duration: 3000 });
      this.success.set('Prescripciones actualizadas con éxito');
    }
    catch (e) {
      const errorResponse = e as HttpErrorResponse;
      const problemDetails = errorResponse.error as ProblemDetails;
      this.error.set(problemDetails.detail);
      this.snackBar.open(problemDetails.detail, 'Cerrar', { duration: 3000 });
    }
  }
}
