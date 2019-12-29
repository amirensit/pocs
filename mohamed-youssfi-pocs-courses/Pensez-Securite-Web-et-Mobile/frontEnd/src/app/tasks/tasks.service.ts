import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {HOST} from '../app.constants';
import {JwtService} from '../service/jwt.service';

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  constructor(private http: HttpClient, private jwtService: JwtService) {
  }

  getTasks() {
    return this.http.get(HOST + '/tasks',
      {headers: new HttpHeaders({Authorization: 'Bearer ' + this.jwtService.loadToken()})});
  }
}
