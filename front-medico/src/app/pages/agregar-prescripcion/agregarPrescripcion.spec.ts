import { ComponentFixture, TestBed } from '@angular/core/testing';

// @ts-ignore
import { AgregarPrescripcionComponent } from "./agregarPrescripcion.component";

describe('AgregarPrescripcionComponent', () => {
  let component: AgregarPrescripcionComponent;
  let fixture: ComponentFixture<AgregarPrescripcionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AgregarPrescripcionComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(AgregarPrescripcionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
