import { Injectable } from '@angular/core';
import { KeycloakInstance } from 'keycloak-js';

declare var Keycloak: any; // we need to use this line due to javascript code compilation error. (with capital K and not k :p)

@Injectable({
  providedIn: 'root'
})
export class KeycloakSecurityService {
  // public kc: KeycloakInstance;  public scope in order to be used anywhere in the app.
  public kc; // Here we need to not declare the type of kc to avoid  the error of then() callback of init() method.

  constructor() { }

  /*
  this is the first method using await which is not a best practice.
  */
  /* async init() {
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
 } */

  init() {
    console.log('Security Initialisation');
    this.kc = new Keycloak({
      url: "http://localhost:8080/auth/",
      realm: "ecom-realm",
      clientId: "AngularProductsApp"
    });

    /*
    This way app will be initialized before the init method get executed because we are not returning a promise.
    */
    /* this.kc.init({
     onLoad: 'login-required',
     promiseType: 'native' // due to problem with some deprecated promises, we need to add this line.
   }).then(
     authenticated => {
       console.log('resolved authentication');
       console.log(authenticated);
     },
     () => {
       console.log('rejected authentication');
     }
   ).catch(
     error => console.log(error)
     ); */

    return new Promise((resolve, reject) => {
      this.kc.init({
        onLoad: 'login-required',
        promiseType: 'native' // due to problem with some deprecated promises, we need to add this line.
      }).then(
        authenticated => {
          console.log('resolved authentication');
          console.log(authenticated);
          resolve(authenticated);
        }/* ,
        (rejection) => {
          console.log('rejected authentication');
          reject(rejection)
        } */
      ).catch(
        error => {
          console.log(error);
          reject(error);
        }
      );
    });
  }
}
