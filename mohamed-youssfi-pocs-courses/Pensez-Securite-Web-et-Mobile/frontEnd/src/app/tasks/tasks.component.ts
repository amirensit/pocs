import { Component, OnInit } from '@angular/core';
import {TasksService} from './tasks.service';
import {Router} from '@angular/router';
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.scss']
})
export class TasksComponent implements OnInit {

  constructor(private  tasksService: TasksService, private router: Router, private loginService: LoginService) { }

  tasks;
  ngOnInit() {

    this.tasksService.getTasks().subscribe(
      (data) => {
      this.tasks = data;
      },
      () => {
        this.loginService.logout();
        this.router.navigate(['/login']);
      }
    );
  }

  /* testObservable() {
    return new Observable((observer) => {
      observer.next('next method called');
      observer.complete();
    });
  }

  callObservable() {
    this.testObservable().subscribe( {
      next(data) {
        console.log('subscribe method' + data);
      },
      complete() {
        console.log('complete method');
      }
    }
    );
  } */

  onNewTask() {
    this.router.navigate(['/new-task']);
  }

}
