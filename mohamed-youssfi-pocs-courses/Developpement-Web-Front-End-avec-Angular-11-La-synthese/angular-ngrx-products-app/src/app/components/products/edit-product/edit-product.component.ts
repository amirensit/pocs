import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { EditProductAction, UpdateProductAction } from 'src/app/ngrx/products.actions';
import { ProductsState, ProductsStateEnum } from 'src/app/ngrx/products.reducer';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  productId: number | undefined;
  state: ProductsState | null = null;
  productFormGroup: FormGroup | null = null;
  readonly ProductsStateEnum = ProductsStateEnum;
  submitted = false;

  constructor(private activatedRoute: ActivatedRoute, private store: Store<any>,
    private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.productId = Number(this.activatedRoute.snapshot.paramMap.get("id"));
    this.store.dispatch(new EditProductAction(this.productId));
    this.store.subscribe(store => {
      this.state = store.catalogState;
      if(this.state?.dataState == ProductsStateEnum.EDIT) {
        this.productFormGroup = this.fb.group({
          id: [this.state.currentProduct?.id],
          name: [this.state.currentProduct?.name, Validators.required],
          price: [this.state.currentProduct?.price, Validators.required],
          quantity: [this.state.currentProduct?.quantity, Validators.required],
          selected: [this.state.currentProduct?.selected],
          available: [this.state.currentProduct?.available]
        });
      }
    })
  }

  onEditProduct() {
    this.submitted = true;
    if(this.productFormGroup?.invalid) return;
    this.store.dispatch(new UpdateProductAction(this.productFormGroup?.value));
  }

  get fields() {
    return this.productFormGroup?.controls;
  }

  onOkUpdate() {
    this.router.navigate(["/products"]);
  }


}
