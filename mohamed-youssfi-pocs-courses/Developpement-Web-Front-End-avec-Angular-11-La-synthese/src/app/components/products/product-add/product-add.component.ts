import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent implements OnInit {

  productFormGroup: FormGroup = this.fb.group({
    name: ["", Validators.required],
    price: [0, Validators.required],
    quantity: [0, Validators.required],
    selected: [true, Validators.required],
    available: [true, Validators.required],
  });;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void { }

}
