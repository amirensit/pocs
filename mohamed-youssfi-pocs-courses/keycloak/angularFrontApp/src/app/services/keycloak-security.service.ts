import { Injectable } from '@angular/core';
import { KeycloakInstance } from 'keycloak-js';

declare var Keycloak: any; // we need to use this line due to javascript code compilation error. (with capital K and not k :p)

@Injectable({
  providedIn: 'root'
})
export class KeycloakSecurityService {
  public kc: KeycloakInstance; // public scope in order to be used anywhere in the app.

  constructor() { }

   async init() {
    console.log('Security Initialisation');
    this.kc = new Keycloak({
      url: "http://localhost:8080/auth/",
      realm: "ecom-realm",
      clientId: "AngularProductsApp"
    });

    await this.kc.init({
      onLoad: 'login-required',
      promiseType: 'native' // due to problem with some deprecated promises, we need to add this line.
    });
  }

}
