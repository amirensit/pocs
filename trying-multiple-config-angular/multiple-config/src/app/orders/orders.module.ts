import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrdersRoutingModule } from './orders-routing.module';
import { OrdersComponent } from './orders.component';
import { FormatTitlePipe } from './format-title.pipe';
import { MultiStepFormComponent } from './multi-step-form/multi-step-form.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [OrdersComponent, MultiStepFormComponent, FormatTitlePipe],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    OrdersRoutingModule
  ]
})
export class OrdersModule { }
