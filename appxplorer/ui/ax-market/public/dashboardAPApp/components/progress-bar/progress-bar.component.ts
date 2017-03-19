import { Component, Input } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'progress-bar',
    templateUrl: 'progress-bar.component.html',
    styleUrls: ['progress-bar.component.scss']
})

export class ProgressBarComponent {

    @Input('progress') progressValue: number;
    @Input('show-text') showText: boolean;

    @Input() widthPixels: number;
    @Input() heightPixels: number;

    constructor() {
        this.showText = true;
    }

    getWidth() {

        if(this.widthPixels==undefined || this.widthPixels==null || this.widthPixels<0) {
            return 180;
        } else {
            return this.widthPixels;
        }
    }

    getHeight() {

        if(this.heightPixels==undefined || this.heightPixels==null || this.heightPixels<0) {
            return 4;
        } else {
            return this.heightPixels;
        }
    }

    getProgressValue() {
        if(this.progressValue==undefined || this.progressValue==null || this.progressValue<0) {
            return 10;
        } else {
            return this.progressValue;
        }
    }

}
