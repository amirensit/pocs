import { Injectable } from '@angular/core';
import { KeycloakInstance } from 'keycloak-js';

@Injectable({
  providedIn: 'root'
})
export class KeycloakSecurityService {

  constructor() { }

  init() {
    console.log('Security Initialisation');
  }
}
