import { Component, Input, Output, EventEmitter } from '@angular/core';
import { RequestDetail } from '../pendingrequests/RequestDetail';

@Component({
    moduleId: module.id,
    selector: 'request-list',
    templateUrl: 'request-list.component.html',
    styleUrls: ['request-list.component.scss']
})
export class RequestListComponent {
 @Input() requestDetails: RequestDetail[];
  @Input() selected: RequestDetail
  @Output() selectedChange: EventEmitter<RequestDetail> = new EventEmitter();
}
