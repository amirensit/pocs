import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductsComponent } from './products/products.component';
import { SuppliersComponent } from './suppliers/suppliers.component';
import { KeycloakSecurityService } from './services/keycloak-security.service';

function keycloakFactory(keycloakSecurity: KeycloakSecurityService) {
  return () => keycloakSecurity.init()
}

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    SuppliersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    // this will start the init method of KeycloakSecurityService using the APP_INITIALIZER. There is other way using the main.ts file
    { provide: APP_INITIALIZER, deps: [KeycloakSecurityService], useFactory: keycloakFactory, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
