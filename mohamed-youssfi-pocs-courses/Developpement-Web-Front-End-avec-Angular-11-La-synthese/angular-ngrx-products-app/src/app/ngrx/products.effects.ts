import { Injectable } from "@angular/core";
import { ProductService } from "../services/product.service";

@Injectable()
export class ProductsEffects {
  constructor(private productsService: ProductService) {

  }
}
