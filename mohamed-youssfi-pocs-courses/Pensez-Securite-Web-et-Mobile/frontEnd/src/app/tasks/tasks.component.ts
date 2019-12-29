import { Component, OnInit } from '@angular/core';
import {TasksService} from './tasks.service';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.scss']
})
export class TasksComponent implements OnInit {

  constructor(private  tasksService: TasksService, private router: Router) { }

  tasks;
  ngOnInit() {

    this.tasksService.getTasks().subscribe(
      (data) => {
      this.tasks = data;
      },
      () => {
        this.router.navigate(['/login']);
      }
    );
  }

  testObservable() {
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
  }

}
