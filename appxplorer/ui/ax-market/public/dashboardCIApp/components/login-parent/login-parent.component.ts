import { OnInit, Component, ViewChild, AfterViewInit } from '@angular/core';
import { ModalComponent } from 'ng2-bs3-modal/ng2-bs3-modal';
declare var $: 'JQueryStatic';

@Component({
    moduleId: module.id,
    selector: 'login-parent',
    templateUrl: 'login-parent.component.html',
    styleUrls: ['login-parent.component.scss']
})
export class LoginParentComponent implements AfterViewInit {

    @ViewChild('myModal')
    public myModal: ModalComponent;


    close() {
        this.myModal.close();
    }

    open() {
        this.myModal.open();
    }

    ngAfterViewInit() {

        this.myModal.open();
    }
}

