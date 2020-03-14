import { Injectable } from '@angular/core';
import { KeycloakInstance } from 'keycloak-js';

declare let keycloak: any; // we need to use this line due to javascript code compilation error.

@Injectable({
  providedIn: 'root'
})
export class KeycloakSecurityService {

  public kc: KeycloakInstance; // public scope in order to be used anywhere in the app.
  constructor() { }

  init() {
    console.log('Security Initialisation');
    this.kc = new keycloak({
      url: "http://localhost:8080/auth",
      realm: "ecom-realm",
      client_id: "AngularProductsApp"
    });
  }
}
