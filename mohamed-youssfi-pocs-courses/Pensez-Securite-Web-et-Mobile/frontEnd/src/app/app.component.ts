import { Component } from '@angular/core';
import { LoginService } from './login/login.service';
import {Router} from '@angular/router';
import { JwtService } from './service/jwt.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontEnd';

  constructor(private loginService: LoginService, private router: Router, public jwtService: JwtService) { }

  onLogout() {
      this.loginService.logout();
      this.router.navigate(['/login']);
  }
}
