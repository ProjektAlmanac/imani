import { Injectable } from '@angular/core';
import {Prescripcion} from "../../generated/openapi";

@Injectable({
  providedIn: 'root'
})
export class ColorService {

  constructor() { }
  public generaColor(precipcion: Prescripcion): string {
    if(precipcion.figura === 'cuadrado'){
      return '#ADD8E6'
    }
    if(precipcion.figura === 'circulo'){
      return '#90EE90'
    }
    if(precipcion.figura === 'triangulo'){
      return '#FFFFE0'
    }
    if(precipcion.figura === 'estrella'){
      return '#FFDAB9'
    }
    if(precipcion.figura === 'anillo'){
      return '#FFB6C1'
    }
    if(precipcion.figura === 'semicirculo'){
      return '#ed6f95'
    }
    if (precipcion.figura === 'nube'){
      return '#c3b6ff'
    }
    return '#FFFFFF';
  }

}
