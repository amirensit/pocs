import { Component, OnInit } from '@angular/core';
import { SuppliersService } from '../services/suppliers.service';

@Component({
  selector: 'app-suppliers',
  templateUrl: './suppliers.component.html',
  styleUrls: ['./suppliers.component.css']
})
export class SuppliersComponent implements OnInit {

  suppliers;
  errormsg;
  constructor(private supplierService: SuppliersService) { }

  ngOnInit(): void {
    this.supplierService.getSuppliers().subscribe(
      data => {
        this.suppliers = data; 
      },
      error => {
        this.errormsg = error.error.message
      }
    )
  }

}
