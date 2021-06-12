import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { NewProductAction, SaveProductAction } from 'src/app/ngrx/products.actions';
import { ProductsState, ProductsStateEnum } from 'src/app/ngrx/products.reducer';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit {

  productFormGroup: FormGroup | null = null;
  state: ProductsState | null = null;
  readonly ProductsStateEnum = ProductsStateEnum;
  submitted: boolean = false;

  constructor(private store: Store<any>, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.store.dispatch(new NewProductAction({}));
    this.store.subscribe(state => {
      this.state = state.catalogState;
      if(this.state?.dataState == ProductsStateEnum.NEW) {
        this.submitted = false;
        this.productFormGroup = this.fb.group({
          name: ["", Validators.required],
          price: [0, Validators.required],
          quantity: [0, Validators.required],
          selected: [true, Validators.required]
        });
      }
    });
  }

  onNewProduct() {
    this.store.dispatch(new NewProductAction({}));
  }

  onSaveProduct() {
    this.submitted = true;
    this.store.dispatch(new SaveProductAction(this.productFormGroup?.value));
  }

  get fields() {
    return this.productFormGroup?.controls;
  }

}
