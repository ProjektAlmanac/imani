import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ColorService {

  constructor() { }
  public getRandomColor() {
    const base = 255;
    const pastelFactor = 100; // Factor para asegurar que los colores sean más claros

    const r = Math.round((Math.random() * (base - pastelFactor)) + pastelFactor).toString(16).padStart(2, '0');
    const g = Math.round((Math.random() * (base - pastelFactor)) + pastelFactor).toString(16).padStart(2, '0');
    const b = Math.round((Math.random() * (base - pastelFactor)) + pastelFactor).toString(16).padStart(2, '0');

    return `#${r}${g}${b}`;
  }

}
