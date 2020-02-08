import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';

let emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";
@Component({
  selector: 'app-customers-first-second',
  templateUrl: './customers-first-second.component.html',
  styleUrls: ['./customers-first-second.component.scss']
})
export class CustomersFirstSecondComponent implements OnInit {
  public usersForm: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit() {
    this.usersForm = this.fb.group({
      date: this.fb.control(new Date()),
      users: this.fb.array([
        this.fb.group({
          firstName: [{value: 'user 1', disabled: false}, Validators.required],
          lastName: [{value: '', disabled: false}, Validators.required],
          email: [{value: '', disabled: false}, Validators.pattern(emailRegex)]
        }),
        this.fb.group({
          firstName: [{value: 'user 2', disabled: true}, Validators.required],
          lastName: [{value: '', disabled: false}, Validators.required],
          email: [{value: '', disabled: false},  Validators.pattern(emailRegex)]
        })
      ])
    })
  }

  removeFormControl(i) {
    let usersArray = this.usersForm.controls.users as FormArray;
    usersArray.removeAt(i);
  }

  addFormControl() {
    let usersArray = this.usersForm.controls.users as FormArray;
    let arraylen = usersArray.length;

    let newUsergroup: FormGroup = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.pattern(emailRegex)]
    })

    usersArray.insert(arraylen, newUsergroup);
  }

  onSubmit() {
    console.log(this.usersForm.value);
  }
}