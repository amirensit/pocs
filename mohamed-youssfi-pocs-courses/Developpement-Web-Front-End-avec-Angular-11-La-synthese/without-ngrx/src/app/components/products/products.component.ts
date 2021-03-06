import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ProductsService } from 'src/app/services/products.service';
import { catchError, map, startWith } from 'rxjs/operators';
import { ActionEvent, AppDataState, DataStateEnum, ProductActionsTypes } from 'src/app/state/product.state';
import { Product } from 'src/app/models/product.model';
import { Router } from '@angular/router';
import { EventDrivenService } from 'src/app/state/event.driver.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products$: Observable<AppDataState<Product[]>> | null = null;
  readonly DataStateEnum = DataStateEnum;

  constructor(private productService: ProductsService,
    private router: Router,
    private eventDriverService: EventDrivenService) { }

  ngOnInit(): void {
    this.eventDriverService.sourceEventSubjectObservable.subscribe(
      (actionEvent: ActionEvent) => {
        this.onActionEvent(actionEvent);
      }
    )
  }

  onActionEvent(event: ActionEvent) {
    switch (event.type) {
      case ProductActionsTypes.GET_ALL_PRODUCTS:
        this.ongetAllProducts();
        break;
      case ProductActionsTypes.GET_SELECTED_PRODUCTS:
        this.ongetSelectedProducts();
        break;
      case ProductActionsTypes.GET_AVAILABLE_PRODUCTS:
        this.ongetAvailableProducts();
        break;
      case ProductActionsTypes.SEARCH_PRODUCTS:
        this.onSearch(event.payload);
        break;
      case ProductActionsTypes.NEW_PRODUCT:
        this.onNewProduct();
        break;
        case ProductActionsTypes.SELECT_PRODUCT:
          this.onSelect(event.payload);
          break;
        case ProductActionsTypes.EDIT_PRODUCT:
          this.onEditProduct(event.payload);
          break;
        case ProductActionsTypes.DELETE_PRODUCT:
          this.onDeleteProduct(event.payload);
          break;

    }
  }

  ongetAllProducts() {
    this.products$ = this.productService.getAllProducts().pipe(
      map(data => ({ appDataState: DataStateEnum.LOADED, data })),
      startWith({ appDataState: DataStateEnum.LOADING }),
      catchError(error => of({ appDataState: DataStateEnum.ERROR, errorMsg: error }))
    );
  }

  ongetAvailableProducts() {
    this.products$ = this.productService.getAvailableProduct().pipe(
      map(data => ({ appDataState: DataStateEnum.LOADED, data })),
      startWith({ appDataState: DataStateEnum.LOADING }),
      catchError(error => of({ appDataState: DataStateEnum.ERROR, errorMsg: error }))
    );
  }

  ongetSelectedProducts() {
    this.products$ = this.productService.getSelectedProduct().pipe(
      map(data => ({ appDataState: DataStateEnum.LOADED, data })),
      startWith({ appDataState: DataStateEnum.LOADING }),
      catchError(error => of({ appDataState: DataStateEnum.ERROR, errorMsg: error }))
    );
  }

  onSearch(dataForm: any) {
    this.products$ = this.productService.searchProducts(dataForm.keyword).pipe(
      map(data => ({ appDataState: DataStateEnum.LOADED, data })),
      startWith({ appDataState: DataStateEnum.LOADING }),
      catchError(error => of({ appDataState: DataStateEnum.ERROR, errorMsg: error }))
    );
  }

  onSelect(product: Product) {
    this.productService.selectProduct(product).subscribe(
      (p: Product) => product.selected = p.selected
    );
  }

  onDeleteProduct(product: Product) {
    this.productService.deleteProduct(product).subscribe(
      () => this.ongetAllProducts()
    );
  }

  onNewProduct() {
    this.router.navigate(['/newProduct']);
  }

  onEditProduct(product: Product) {
    this.router.navigate(['/editProduct', product.id]);
  }
}
