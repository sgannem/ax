import { Component,OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'show-add-new-app-technical-details',
    templateUrl: 'add-new-app-technical.component.html',
    styleUrls: ['add-new-app-technical.component.scss']
})
export class AddNewAppTechnicalDetailsComponent implements OnInit {
private fileNo: number;

    ngOnInit()
    {
        this.fileNo = 1;
    }

    incrementFile()
    {
        this.fileNo++;
    }

    decrementFile()
    {
        if(this.fileNo > 1)
        {
            this.fileNo--;
        }
        
    }
}

