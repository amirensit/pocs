import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormArray, Validators } from '@angular/forms';

let emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";
@Component({
  selector: 'app-customers-first-first',
  templateUrl: './customers-first-first.component.html',
  styleUrls: ['./customers-first-first.component.scss']
})
export class CustomersFirstFirstComponent implements OnInit {
  public usersForm = new FormArray([
    new FormGroup({
      firstName: new FormControl('user 1', {validators: Validators.required}), // using AbstractControlOptions as validators.
      lastName: new FormControl('', Validators.required), // the validator syntax is similar to the previous one !
      email: new FormControl('', {validators: Validators.pattern(emailRegex)})
    }),
    new FormGroup({
      firstName: new FormControl('user 2', {validators: Validators.required}),
      lastName: new FormControl('', {validators: Validators.required}),
      email: new FormControl('', {validators: Validators.pattern(emailRegex)})
    })
  ]);

  constructor() { }

  ngOnInit() { }

  onSubmit() {
    console.log(this.usersForm.value);
    console.log(this.usersForm);
  }
}