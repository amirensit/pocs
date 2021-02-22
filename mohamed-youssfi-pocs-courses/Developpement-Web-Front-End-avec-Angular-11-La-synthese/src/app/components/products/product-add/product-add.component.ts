import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent implements OnInit {

  public submited: boolean = false;

  productFormGroup: FormGroup = this.fb.group({
    name: ["", Validators.required],
    price: [0, Validators.required],
    quantity: [0, Validators.required],
    selected: [true, Validators.required],
    available: [true, Validators.required],
  });

  constructor(private fb: FormBuilder, private productService: ProductsService) { }

  ngOnInit(): void { }

  onSaveProduct() {
    this.submited = true;
    if (this.productFormGroup.invalid) return;
    this.productService.saveProduct(this.productFormGroup.value).subscribe(
      () => alert("success")
    );
  }

}
