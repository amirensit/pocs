import { Injectable } from '@angular/core';
import {HOST} from '../app.constants';
import {JwtService} from '../service/jwt.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private jwtService: JwtService) { }

  login(user) {
    return this.jwtService.login(user);
  }

  logout() {
    this.jwtService.logout().subscribe();
  }


}

