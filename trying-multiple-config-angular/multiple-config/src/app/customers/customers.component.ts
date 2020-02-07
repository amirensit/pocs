import { Component, OnInit } from '@angular/core';
import * as _ from 'lodash';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {
  initialArray = [1, 2, 3, 4, 5, 'a'];

  constructor() { }

  ngOnInit() {
   let afterChunk = _.chunk(['a', 'b', 'c', 'd'], 2);
   console.log(afterChunk);

   let compactedArray = _.compact([0, 1, false, 2, '', 3]);
   console.log(compactedArray);

   let differencedArray = _.difference(this.initialArray, [1, 'a']);
   console.log('differencedArray ' +  differencedArray); // Unlike _.pullAll, this method returns a new array.

   //////////////////////////// now _.differenceBy(array, [values], [iteratee=_.identity])

   let arrayToExclude = [
    {
      'firstName': 'ahmed',
      'lastName': 'rebai',
      'age': 25
     },
     {
      'firstName': 'fehmi',
      'lastName': 'barguelil',
      'age': 27
     }
   ];
   console.log('differenceByMethod' + JSON.stringify(_.differenceBy(
    [
      {
        'firstName': 'amir',
        'lastName': 'choubani',
        'age': 25
      },
      {
       'firstName': 'ahmed',
       'lastName': 'rebai',
       'age': 25
      },
      {
       'firstName': 'fehmi',
       'lastName': 'barguelil',
       'age': 27
      }
    ], arrayToExclude,
    'firstName'
  ) 
  ) 
   )
  }

}
