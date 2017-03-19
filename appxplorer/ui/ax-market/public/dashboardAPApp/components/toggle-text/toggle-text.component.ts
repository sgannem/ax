import { Component, Input } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'toggle-text',
    templateUrl: 'toggle-text.component.html',
    styleUrls: ['toggle-text.component.scss']
})

export class ToggleTextComponent {

    @Input('show-text-content') showText: string;
    @Input('hide-text-content') hideText: string;
    @Input('display-text') displayText: string;

    constructor() {

    }

}
