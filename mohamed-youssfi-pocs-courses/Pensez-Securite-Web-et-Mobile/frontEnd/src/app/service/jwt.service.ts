import {Injectable} from '@angular/core';
import {HOST} from '../app.constants';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable(
  {providedIn: 'root'}
)
export class JwtService {
  constructor(private httpClient: HttpClient) {
  }

  login(user) {
    function authenticateSuccess(resp) {
      const jwt = resp.headers.get('Authorization');
      this.saveToken(jwt);
      return jwt;
    }

    return this.httpClient.post(HOST + '/login', user, {observe: 'response'})
      .pipe(
        map(authenticateSuccess.bind(this))
      );
  }
  
  saveToken(jwt: string) {
    localStorage.setItem('token', jwt);
  }

  loadToken() {
    return localStorage.getItem('token');
  }

}
