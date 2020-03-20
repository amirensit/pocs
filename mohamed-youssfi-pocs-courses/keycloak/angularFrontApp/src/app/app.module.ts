import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER, DoBootstrap, ApplicationRef } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductsComponent } from './products/products.component';
import { SuppliersComponent } from './suppliers/suppliers.component';
import { KeycloakSecurityService } from './services/keycloak-security.service';
import { RequestInterceptorService } from './services/request-interceptor.service';

function keycloakFactory(keycloakSecurity: KeycloakSecurityService) {
  return () => keycloakSecurity.init()
}

/*
this is an other alternative for using APP_INITIALIZER provider using DoBootstrap interface.
*/
const keyCloakService = new KeycloakSecurityService();

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
    { provide: APP_INITIALIZER, deps: [KeycloakSecurityService], useFactory: keycloakFactory, multi: true },
    // { provide: keyCloakService, useValue: keyCloakService }, // useValue for an insantiated class and useClass for a declared class.
    { provide: HTTP_INTERCEPTORS, useClass: RequestInterceptorService, multi: true }
  ],
  bootstrap: [AppComponent],
  // entryComponents: [ AppComponent ]
})
export class AppModule { }

/* export class AppModule implements DoBootstrap {
  ngDoBootstrap(appRef: ApplicationRef): void {
    keyCloakService.init()
      .then(
        (authenticated) => {
          if (authenticated) appRef.bootstrap(AppComponent);
        }
      )
      .catch(
        (error) => console.log(error)
      )
  }
}
  */

