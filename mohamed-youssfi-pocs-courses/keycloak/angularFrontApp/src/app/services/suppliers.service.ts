import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { KeycloakSecurityService } from './keycloak-security.service';

@Injectable({
  providedIn: 'root'
})
export class SuppliersService {

  constructor(private httpClient: HttpClient, 
    private keycloakService: KeycloakSecurityService) { }

  getSuppliers() {
    return this.httpClient.get("http://localhost:8083/suppliers",
    {
      headers: new HttpHeaders({
      Authorization: 'Bearer ' + this.keycloakService.kc.token
    }
    )
  }
    )
  }
}
