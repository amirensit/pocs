import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {TasksComponent} from './tasks/tasks.component';
import {RegistrationComponent} from './registration/registration.component';
import {NewTaskComponent} from './new-task/new-task.component';


const routes: Routes = [
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'tasks', component: TasksComponent
  },
  {
    path: 'new-task', component: NewTaskComponent
  },
  {
    path: 'register', component: RegistrationComponent
  },
  {
    path: '', redirectTo: 'login', pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
