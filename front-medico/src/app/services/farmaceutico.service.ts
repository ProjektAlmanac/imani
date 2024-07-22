import { Injectable } from '@angular/core';
import {
  FarmaceuticoService as FarmaceuticoApi,
  NuevoFarmaceutico,
} from '../../generated/openapi';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FarmaceuticoService {
  constructor(private farmaceuticoApi: FarmaceuticoApi) {}

  public crearFarmaceutico(farmaceutico: NuevoFarmaceutico) {
    return lastValueFrom(this.farmaceuticoApi.postFarmaceutico(farmaceutico));
  }
}
