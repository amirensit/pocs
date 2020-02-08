import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CustomersFirstComponent } from './customers-first.component';
import { CustomersFirstFirstComponent } from './customers-first-first/customers-first-first.component';
import { CustomersFirstSecondComponent } from './customers-first-second/customers-first-second.component';

const routes: Routes = [
  { path: '', component: CustomersFirstComponent, children: [
    {
      path: 'first-first',
      component: CustomersFirstFirstComponent
    },
    {
      path: 'first-second',
      component: CustomersFirstSecondComponent
    },
    {
      path: '',
      redirectTo: '',
      pathMatch: 'full'
    }
  ] 
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomersFirstRoutingModule { }
