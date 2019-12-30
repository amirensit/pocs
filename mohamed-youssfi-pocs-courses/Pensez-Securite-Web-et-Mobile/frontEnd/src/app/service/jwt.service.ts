import {Injectable} from '@angular/core';
import {HOST} from '../app.constants';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {JwtHelperService} from '@auth0/angular-jwt';
import {RoleModel} from '../role.model';

@Injectable(
  {providedIn: 'root'}
)
export class JwtService {

  private roles: RoleModel[];

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
    const jwtHelper = new JwtHelperService();
    this.roles = jwtHelper.decodeToken(jwt).roles;
  }

  loadToken() {
    return localStorage.getItem('token');
  }

  logout() {
    return new Observable(observer => {
      localStorage.removeItem('token');
      observer.complete();
    });
  }

   getRoles(): Observable<RoleModel[]> {
    return new Observable<RoleModel[]>((observer) =>
      observer.next(this.roles)
    );
  }

}
