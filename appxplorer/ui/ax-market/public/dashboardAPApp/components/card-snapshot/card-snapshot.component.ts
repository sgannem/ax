import { Component, Input } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'card-snapshot',
    templateUrl: 'card-snapshot.component.html',
    styleUrls: ['card-snapshot.component.scss']
})

export class CardSnapshotComponent {

    @Input() name: string;
    @Input() spaceLeftOnCardPercent: string;
    @Input() showSpaceInformation: boolean;
    @Input() updatedOn: string;
    @Input() tokenKey: string;
    @Input('display-mode') displayMode: string;

    constructor() {

    }
}
