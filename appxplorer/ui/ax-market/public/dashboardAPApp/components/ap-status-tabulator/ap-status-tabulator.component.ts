import { Component, Input } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'ap-status-tabulator',
    templateUrl: 'ap-status-tabulator.component.html',
    styleUrls: ['ap-status-tabulator.component.scss']
})

export class ApStatusTabulatorComponent {

    @Input('data-json-array') dataJsonArray: string[] = [];

    constructor() {

    }

}
