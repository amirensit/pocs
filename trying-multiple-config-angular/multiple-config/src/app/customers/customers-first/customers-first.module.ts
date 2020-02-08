import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomersFirstRoutingModule } from './customers-first-routing.module';
import { CustomersFirstComponent } from './customers-first.component';
import { CustomersFirstFirstComponent } from './customers-first-first/customers-first-first.component';
import { CustomersFirstSecondComponent } from './customers-first-second/customers-first-second.component';

/*
Command used for nested module generation
ng g m customers-first --module /customers/customers.module --route customers-first
*/

@NgModule({
  declarations: [CustomersFirstComponent, CustomersFirstFirstComponent, CustomersFirstSecondComponent],
  imports: [
    CommonModule,
    CustomersFirstRoutingModule
  ]
})
export class CustomersFirstModule { }
