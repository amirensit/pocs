import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductsComponent } from './products/products.component';
import { SuppliersComponent } from './suppliers/suppliers.component';
import { KeycloakSecurityService } from './services/keycloak-security.service';
import { from } from 'rxjs';

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
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    // this will start the init method of KeycloakSecurityService using the APP_INITIALIZER. There is other way using the main.ts file
    { provide: APP_INITIALIZER, deps: [KeycloakSecurityService], useFactory: keycloakFactory, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
