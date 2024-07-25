import {
  Component,
  Input,
  signal,
  effect,
  WritableSignal,
} from '@angular/core';
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
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';
import { PrescripcionService } from '../../services/prescripcion.service';
import { Router } from '@angular/router';
import {
  ProblemDetails,
  Paciente,
  NuevaPrescripcion,
} from '../../../generated/openapi';
import { CommonModule, Location } from '@angular/common';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, MatOption } from '@angular/material/core';
import { MatSelect } from '@angular/material/select';

interface PrescripcionTabla {
  medicamento: string;
  frecuenciaDosis: string;
  indicaciones: string;
  duracion: string;
  cantidadPorDosis: string;
  inicio: string;
  fechaInicio: Date | null;
  horaInicio: string;
}

@Component({
  selector: 'app-agregarPrescripcion',
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
    MatOption,
    MatSelect,
  ],
  templateUrl: './agregarPrescripcion.html',
  styleUrls: ['./agregarPrescripcion.component.scss'],
})
export class AgregarPrescripcionComponent {
  @Input() paciente: Paciente | undefined;

  prescripcionForm: FormGroup = new FormGroup({
    medicamento: new FormControl<string>('', Validators.required),
    frecuenciaDosis: new FormControl<number | null>(null, Validators.required),
    unidadFrecuenciaDosis: new FormControl<string>(
      'horas',
      Validators.required
    ),
    indicaciones: new FormControl<string>('', Validators.required),
    duracion: new FormControl<number | null>(null, Validators.required),
    unidadDuracion: new FormControl<string>('dias', Validators.required),
    cantidadPorDosis: new FormControl<string>('', Validators.required),
    fechaInicio: new FormControl<Date | null>(null, Validators.required),
    horaInicio: new FormControl<string>('00:00'),
  });

  error = signal<string | undefined>(undefined);
  success = signal<string | undefined>(undefined);
  prescripciones: WritableSignal<PrescripcionTabla[]> = signal<
    PrescripcionTabla[]
  >([]);
  dataSource = new MatTableDataSource<PrescripcionTabla>();

  displayedColumns: string[] = [
    'medicamento',
    'frecuenciaDosis',
    'indicaciones',
    'duracion',
    'cantidadPorDosis',
    'fechaInicio',
    'horaInicio',
  ];

  constructor(
    private router: Router,
    private prescripcionService: PrescripcionService,
    private snackBar: MatSnackBar,
    private location: Location
  ) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras?.state?.['paciente']) {
      this.paciente = navigation.extras.state['paciente'];
    }

    effect(() => {
      this.dataSource.data = this.prescripciones();
    });
  }

  agregarPrescripcion() {
    if (!this.prescripcionForm.valid) return;
    const formValue = this.prescripcionForm.value;
    const horaInicio = formValue.horaInicio ? formValue.horaInicio : '00:00';
    const fechaHoraISO = this.formatToISODateTime(
      formValue.fechaInicio,
      horaInicio
    );
    const nuevaPrescripcion: PrescripcionTabla = {
      medicamento: formValue.medicamento,
      frecuenciaDosis: `${formValue.frecuenciaDosis} ${formValue.unidadFrecuenciaDosis}`,
      indicaciones: formValue.indicaciones,
      duracion: `${formValue.duracion} ${formValue.unidadDuracion}`,
      cantidadPorDosis: formValue.cantidadPorDosis,
      inicio: fechaHoraISO,
      fechaInicio: formValue.fechaInicio
        ? new Date(formValue.fechaInicio)
        : null,
      horaInicio: horaInicio,
    };
    this.prescripciones.set([...this.prescripciones(), nuevaPrescripcion]);
    this.prescripcionForm.reset();
    this.prescripcionForm.get('horaInicio')?.setValue('00:00');
  }

  formatToISODateTime(date: Date | null, time: string): string {
    if (!date) return '';
    const [hours, minutes] = time.split(':').map(Number);
    const dateObj = new Date(date);

    dateObj.setHours(hours, minutes, 0, 0);

    const year = dateObj.getFullYear();
    const month = (dateObj.getMonth() + 1).toString().padStart(2, '0');
    const day = dateObj.getDate().toString().padStart(2, '0');
    const hour = dateObj.getHours().toString().padStart(2, '0');
    const minute = dateObj.getMinutes().toString().padStart(2, '0');
    const second = dateObj.getSeconds().toString().padStart(2, '0');

    return `${year}-${month}-${day}T${hour}:${minute}:${second}.000Z`;
  }

  async onSubmit() {
    if (this.prescripciones().length === 0) return;

    this.error.set(undefined);
    this.success.set(undefined);

    const convertToSeconds = (value: number, unit: string): number => {
      let seconds = 0
      switch (unit) {
        case 'minutos':
          seconds = value * 60;
          break;
        case 'horas':
          seconds = value * 3600;
          break;
        case 'dias':
          seconds = value * 86400;
          break;
        default:
          seconds = value;
      }
      return Math.round(seconds)
    };

    const prescripciones: NuevaPrescripcion[] = this.prescripciones().map(
      ({
        fechaInicio,
        horaInicio,
        frecuenciaDosis,
        duracion,
        cantidadPorDosis,
        ...rest
      }) => {
        const [frecuencia, unidadFrecuencia] = frecuenciaDosis.split(' ');
        const [duracionValor, unidadDuracion] = duracion.split(' ');

        return {
          ...rest,
          frecuenciaDosis: convertToSeconds(
            parseFloat(frecuencia),
            unidadFrecuencia
          ),
          duracion: convertToSeconds(parseFloat(duracionValor), unidadDuracion),
          cantidadPorDosis: parseFloat(cantidadPorDosis),
          inicio: rest.inicio,
          numeroDeDosis: 0,
        };
      }
    );

    try {
      await this.prescripcionService.crearPrescripcion(
        this.paciente?.id || 1,
        prescripciones
      );
      this.snackBar.open('Prescripción creada con éxito', 'Cerrar', {
        duration: 3000,
      });
      this.success.set('Prescripción creada con éxito'); // Establece el mensaje de éxito
      this.router.navigate(['/pacientes', this.paciente?.id], {
        state: { paciente: this.paciente },
      });
    } catch (e) {
      const errorResponse = e as HttpErrorResponse;
      const problemDetails = errorResponse.error as ProblemDetails;
      this.error.set(problemDetails.detail);
      this.snackBar.open(problemDetails.detail, 'Cerrar', { duration: 3000 });
    }
  }

  async cerrarComponente() {
    this.location.back();
  }
}
