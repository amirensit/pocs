import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product.model';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {

  productId? : number;
  submited: boolean = false;
  productFormGroup = this.fb.group({
    id: [],
    name: [null, Validators.required],
    price: [null, Validators.required],
    quantity: [null, Validators.required],
    selected: [null, Validators.required],
    available: [null, Validators.required],
  });

  constructor(private activatedRoute: ActivatedRoute,
    private productService: ProductsService,
    private fb: FormBuilder) { }

  ngOnInit(): void {
    // this.productId = this.activatedRoute.snapshot.params.id;
    this.activatedRoute.params.subscribe(
      data => {
        this.productId = data['id'];
        this.productService.getProduct(this.productId).subscribe(
          (product: Product) => this.productFormGroup.patchValue({
            id: product.id,
            name: product.name,
            quantity: product.quantity,
            price: product.price,
            selected: product.selected,
            available: product.available
          })
        );
      }
    );
  }

  onUpdateProduct() {
    this.productService.updateProduct(this.productFormGroup.value).subscribe(
      () => alert("success update")
    )
  }

}
