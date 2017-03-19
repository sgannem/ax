import { Component, Input } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'dropdown-action',
    templateUrl: 'dropdown-action.component.html',
    styleUrls: ['dropdown-action.component.scss']
})

export class DropdownActionComponent {

    @Input('btn-display-text') btnDisplayText: string;
    @Input('btn-actions') actionList: ActionList[] = [];
    
    constructor() {
        
    }
}

export interface ActionList {
    btn_name: string;
    btn_action: string;
}
