import { Component, Input, Output, EventEmitter } from '@angular/core';
import { RequestDetail } from '../pendingrequests/RequestDetail';

@Component({
    moduleId: module.id,
    selector: 'request-detail',
    templateUrl: 'request-detail.component.html',
    styleUrls: ['request-detail.component.scss']
})
export class RequestDetailComponent {
@Input() requestDetail: RequestDetail
}
