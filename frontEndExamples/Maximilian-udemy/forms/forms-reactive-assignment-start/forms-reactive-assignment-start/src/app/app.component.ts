import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit  {

  signupForm: FormGroup;
  status = ['Stable', 'Critical', 'Finished'];

  ngOnInit() {
    this.signupForm = new FormGroup({
      'projectName': new FormControl(null, Validators.required, this.forbiddenProjectNameAsync),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'status': new FormControl('Stable')
    });
  }

  /* forbiddenProjectName(control: FormControl): {[s: string]: boolean} {
    if (control.value === 'Test') {
      return {'forbiddenProjectName': true};
    }
    return null;
  } */

  forbiddenProjectNameAsync(control: FormControl): Promise<any> | Observable<any> {
    const promise = new Promise<any>((resolve, reject) => {
      setTimeout(() => {
        if (control.value === 'Test') {
          resolve({'forbiddenProjectName': true})
        } else {
          resolve(null);
        }
      }, 1500);
    });
    return promise;
  }

  onSubmit() {
    console.log(this.signupForm);
  }
}
