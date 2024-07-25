import { Injectable } from '@angular/core';
import {Login, LoginService as LoginApiService} from '../../generated/openapi';
import {lastValueFrom} from "rxjs";
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private loginService: LoginApiService) { }

  public logea(login: Login){
    return lastValueFrom(this.loginService.login(login));
  }
}
