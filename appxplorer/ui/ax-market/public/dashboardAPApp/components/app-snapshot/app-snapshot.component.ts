import { Component, Input } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'app-snapshot',
    templateUrl: 'app-snapshot.component.html',
    styleUrls: ['app-snapshot.component.scss']
})

export class AppSnapshotComponent {

    @Input() name: string;
    @Input() spaceLeftOnCardPercent: string;
    @Input() showSpaceInformation: boolean;
    @Input() updatedOn: string;
    @Input() tokenKey: string;
    @Input('display-mode') displayMode: string;

    constructor() {

    }
}
