import { Injectable } from '@angular/core';
import {
  DoctorService as DoctorApi,
  NuevoDoctor,
} from '../../generated/openapi';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DoctorService {
  constructor(private doctorApi: DoctorApi) {}

  public crearDoctor(doctor: NuevoDoctor) {
    return lastValueFrom(this.doctorApi.postDoctor(doctor));
  }
}
