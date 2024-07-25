import { TestBed } from '@angular/core/testing';

import { PrescripcionService } from './prescripcion.service';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';

describe('PrescripcionService', () => {
  let service: PrescripcionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    });
    service = TestBed.inject(PrescripcionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
