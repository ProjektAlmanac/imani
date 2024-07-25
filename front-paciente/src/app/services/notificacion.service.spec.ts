import { TestBed } from '@angular/core/testing';

import { NotificacionService } from './notificacion.service';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { Prescripcion } from 'src/generated/openapi';

describe('NotificacionesService', () => {
  let service: NotificacionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    });
    service = TestBed.inject(NotificacionService);
  });

  it("Should calculate time until next dosis", () => {
    // Arrange
    const inicio = new Date(2000, 1, 1, 12)
    const duracion = Number.MAX_SAFE_INTEGER;
    const frecuenciaDosis = 12 * 60 * 60 // 12 horas
    const fechaActual = new Date(2000, 1, 2, 11)
    const prescripcion: Prescripcion = {
      inicio: inicio.toISOString(),
      duracion,
      frecuenciaDosis,
      cantidadPorDosis: 0,
      figura: 'anillo',
      id: 0,
      indicaciones: "",
      medicamento: "",
      numeroDeDosis: 0,
      idDoctor: 0,
      identificador: ""
    }

    // Act
    const resultado = service.calcularTiempoParaSiguienteToma(prescripcion, fechaActual.valueOf());

    // Assert
    expect(resultado).toEqual((inicio.valueOf() + frecuenciaDosis * 2000) - fechaActual.valueOf())
  })
});
