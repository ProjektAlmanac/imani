import { ComponentFixture, TestBed } from '@angular/core/testing';

// @ts-ignore
import { ActualizarPacienteComponent } from "./actualizarPaciente.component";

describe('ActualizarPacienteComponent', () => {
  let component: ActualizarPacienteComponent;
  let fixture: ComponentFixture<ActualizarPacienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActualizarPacienteComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ActualizarPacienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
