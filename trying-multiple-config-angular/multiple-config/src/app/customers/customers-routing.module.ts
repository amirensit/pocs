import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CustomersComponent } from './customers.component';
import { Customers404Component } from './customers404/customers404.component';

const routes: Routes = [
  { 
    path: '',
    component: CustomersComponent,
    children: [
      {
        path: '',
        redirectTo: '',
        pathMatch: 'full'
      },
      { 
        path: 'customers-first',
        loadChildren: () => import('./customers-first/customers-first.module').then(m => m.CustomersFirstModule) 
      },
      {
        path: '**',
        component: Customers404Component
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomersRoutingModule { }
