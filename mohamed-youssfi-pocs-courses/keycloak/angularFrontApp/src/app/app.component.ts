import { Component, OnInit } from '@angular/core';
import { KeycloakSecurityService } from './services/keycloak-security.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'angularFrontApp';

  constructor(public ksService: KeycloakSecurityService) {

  }

  ngOnInit() {
    console.log(this.ksService.kc.tokenParsed);
  }

  onLogin() {
    this.ksService.kc.login();
  }

  onLogout() {
    this.ksService.kc.logout();
  }

  onChangePassword() {
    this.ksService.kc.accountManagement();
  }

  isAppManager() {
    return this.ksService.kc.hasRealmRole("app-manager");
  }
}
