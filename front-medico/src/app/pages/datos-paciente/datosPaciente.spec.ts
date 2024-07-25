import { ComponentFixture, TestBed } from '@angular/core/testing';

// @ts-ignore
import { DatosPacienteComponent } from "./datosPaciente.component";

describe('DatosPacienteComponent', () => {
  let component: DatosPacienteComponent;
  let fixture: ComponentFixture<DatosPacienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DatosPacienteComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(DatosPacienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
