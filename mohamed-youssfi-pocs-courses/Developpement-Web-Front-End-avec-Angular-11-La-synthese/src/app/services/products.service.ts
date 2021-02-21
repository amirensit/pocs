import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<Product[]> {
    let host = environment.host;
    return this.http.get<Product[]>(host + "/products");
  }

  getSelectedProduct(): Observable<Product[]> {
    let host = environment.host;
    let option = { params: new HttpParams().set('selected', "true") }
    return this.http.get<Product[]>(host + "/products", option);
  }

  getAvailableProduct(): Observable<Product[]> {
    let host = environment.host;
    let option = { params: new HttpParams().set('available', "true") }
    return this.http.get<Product[]>(host + "/products", option);
  }

  searchProducts(keyword: string) {
    let host = environment.host;
    let option = { params: new HttpParams().set('name_like', keyword) }
    return this.http.get<Product[]>(host + "/products", option);

  }

  selectProduct(product: Product): Observable<Product> {
    let host = environment.host;
    product.selected = !product.selected;
    return this.http.put<Product>(host + `/products/${product.id}`, product);
  }

  deleteProduct(product: Product): Observable<void> {
    let host = environment.host;
    return this.http.delete<void>(host + `/products/${product.id}`);
  }
}
