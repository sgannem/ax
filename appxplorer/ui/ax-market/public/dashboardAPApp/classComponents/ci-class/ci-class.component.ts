import { Component } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'ci-class',
    templateUrl: 'ci-class.component.html',
    styleUrls: ['ci-class.component.css']
})
export class CiClassComponent {
    id:number;
    ciName:String;
    ciType:String;
    ciDesc:String;
    ciLogo:Blob;
}
