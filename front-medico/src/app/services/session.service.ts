import { Injectable } from '@angular/core';
import { FarmaceuticoService as FarmaceuticoApi } from '../../generated/openapi';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  constructor(private farmaceuticoApi: FarmaceuticoApi) {}

  public getIdSession(): number {
    return 1;
  }
  public isDoctor(): boolean {
    return true;
  }
}
