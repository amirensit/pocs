import { Component } from '@angular/core';
import { environment } from '../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  /*
  in order to use the multi target configuration, we need to use for example: ng serve --configuration=ConfigurationOptionName
  */
  title = environment.myCustomProperty;
}
