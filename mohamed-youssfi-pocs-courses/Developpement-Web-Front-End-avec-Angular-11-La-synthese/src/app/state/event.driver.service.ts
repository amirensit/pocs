import { Injectable } from "@angular/core";
import { Subject } from "rxjs";
import { ActionEvent } from "./product.state";

@Injectable({
  providedIn: 'root'
})
export class EventDrivenService {
  sourceEventSubject: Subject<ActionEvent> = new Subject();
  sourceEventSubjectObservable = this.sourceEventSubject.asObservable();

  publishEvent(event: ActionEvent) {
    this.sourceEventSubject.next(event);
  }
}
