import { Injectable } from '@angular/core';
import { Prescripcion } from 'src/generated/openapi';

@Injectable({
  providedIn: 'root'
})
export class FiguraService {

  private icons: Record<Prescripcion.FiguraEnum, string> = {
    "cuadrado": "square-sharp",
    "circulo": "ellipse-sharp",
    "triangulo": "triangle-sharp",
    "estrella": "star-sharp",
    "anillo": "radio-button-off-sharp",
    "semicirculo": "contrast-sharp",
    "nube": "cloud-sharp"
  }

  private colors: Record<Prescripcion.FiguraEnum, string> = {
    'cuadrado': '#ADD8E6',
    'circulo': '#90EE90',
    'triangulo': '#FFFFE0',
    'estrella': '#FFDAB9',
    'anillo': '#FFB6C1',
    'semicirculo': '#ed6f95',
    'nube': '#c3b6ff'
  };

  constructor() { }

  public getColor(figura: Prescripcion.FiguraEnum | undefined): string {
    if (figura == undefined) return '#FFFFFF'
    return this.colors[figura] || '#FFFFFF'; // Retorna el color asociado o blanco si no existe
  }

  public getIcon(figura: Prescripcion.FiguraEnum | undefined): string {
    if (figura == undefined) return ""
    return this.icons[figura] ?? ""
  }
}
