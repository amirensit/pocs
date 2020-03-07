import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products;
  constructor() { }

  ngOnInit(): void {
    this.products = [
      {id: 1, name: "HP", price: 1200},
      {id: 2, name: "DELL", price: 1400},
      {id: 3, name: "LENOVO", price: 1390}
    ];
  }

}
