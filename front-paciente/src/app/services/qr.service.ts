import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import {
  PacienteService as PacienteAPi,
  PostPacientesSendQrRequest,
} from '../../generated/openapi';
@Injectable({
  providedIn: 'root',
})
export class QrService {
  constructor(private usuario: PacienteAPi) {}
  scanQrCode(imageBlob: Blob): Promise<any> {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.onloadend = () => {
        const imageDataSrc = reader.result as string;
        const request: PostPacientesSendQrRequest = {
          archivo: imageDataSrc.split(',')[1], // Eliminar el prefijo 'data:image/png;base64,'
        };

        lastValueFrom(this.usuario.postPacientesSendQr(1, request))
          .then(resolve)
          .catch(reject);
      };
      reader.onerror = reject;
      reader.readAsDataURL(imageBlob);
    });
  }
}
