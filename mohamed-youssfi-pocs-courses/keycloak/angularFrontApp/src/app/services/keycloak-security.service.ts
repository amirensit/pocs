import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class KeycloakSecurityService {

  constructor() { }

  init() {
    console.log('Security Initialisation');
  }
}
