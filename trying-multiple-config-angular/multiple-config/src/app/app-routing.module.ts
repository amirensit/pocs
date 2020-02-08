import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Page404Component } from './page404/page404.component';


const routes: Routes = [
  { 
    path: 'customers',
    loadChildren: () => import('./customers/customers.module').then(m => m.CustomersModule) 
  },

  { 
    path: 'orders',
    loadChildren: () => import('./orders/orders.module').then(m => m.OrdersModule) 
  },
/*
I didn't get why this route is used, but It seems we need to put this here as mentioned in the official docs
*/
  {
    path: '',
    redirectTo: '',
    pathMatch: 'full'
  },
  {
    path: '**',
    component: Page404Component
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
