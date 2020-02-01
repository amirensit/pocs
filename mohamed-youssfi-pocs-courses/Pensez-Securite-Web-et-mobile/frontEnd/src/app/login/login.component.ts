import { Component, OnInit } from '@angular/core';
import {LoginService} from './login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  error = false;

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit() {
  }

  onLogin(dataForm: any) {
    this.loginService.login(dataForm)
      .subscribe(response => {
        this.router.navigate(['/tasks']);
      },
        error => {
          error = true;
        });
  }
}
