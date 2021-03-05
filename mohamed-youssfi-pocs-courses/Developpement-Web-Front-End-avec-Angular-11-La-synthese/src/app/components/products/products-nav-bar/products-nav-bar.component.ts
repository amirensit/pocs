import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { EventDrivenService } from 'src/app/state/event.driver.service';
import { ActionEvent, ProductActionsTypes } from 'src/app/state/product.state';

@Component({
  selector: 'app-products-nav-bar',
  templateUrl: './products-nav-bar.component.html',
  styleUrls: ['./products-nav-bar.component.css']
})
export class ProductsNavBarComponent implements OnInit {

  // @Output()
  // productEventEmitter: EventEmitter<ActionEvent> = new EventEmitter();

  constructor(private eventDriverService: EventDrivenService) { }

  ngOnInit(): void {
  }

  ongetAllProducts() {
   // this.productEventEmitter.emit({ type: ProductActionsTypes.GET_ALL_PRODUCTS });
   this.eventDriverService.publishEvent({ type: ProductActionsTypes.GET_ALL_PRODUCTS });
  }

  ongetSelectedProducts() {
   // this.productEventEmitter.emit({ type: ProductActionsTypes.GET_SELECTED_PRODUCTS });
   this.eventDriverService.publishEvent({ type: ProductActionsTypes.GET_SELECTED_PRODUCTS });
  }

  ongetAvailableProducts() {
   // this.productEventEmitter.emit({ type: ProductActionsTypes.GET_AVAILABLE_PRODUCTS });
   this.eventDriverService.publishEvent({ type: ProductActionsTypes.GET_AVAILABLE_PRODUCTS });
  }

  onNewProduct() {
   // this.productEventEmitter.emit({ type: ProductActionsTypes.NEW_PRODUCT });
    this.eventDriverService.publishEvent({ type: ProductActionsTypes.NEW_PRODUCT });
  }


  onSearch(dataForm: any) {
   // this.productEventEmitter.emit({ type: ProductActionsTypes.SEARCH_PRODUCTS, payload: dataForm });
    this.eventDriverService.publishEvent({ type: ProductActionsTypes.SEARCH_PRODUCTS, payload: dataForm });
  }

}
