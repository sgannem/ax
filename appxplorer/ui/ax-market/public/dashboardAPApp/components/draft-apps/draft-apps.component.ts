import { Component } from '@angular/core';
import { AppDetail } from '../published-apps/AppDetail';
import { AppProviderDetail } from '../published-apps/AppProviderDetail';

@Component({
    moduleId: module.id,
    selector: 'show-draft-apps',
    templateUrl: 'draft-apps.component.html',
    styleUrls: ['draft-apps.component.scss']
})
export class DraftAppsComponent {
public appDetails: AppDetail[] = [];
constructor() {
        this.appDetails.push(new AppDetail('Bus Pass App','90%', 'Updated on Friday, 20 Jan 2017', '30', '344343353535',
        new AppProviderDetail('App Provider Name', 'provider@email.com','+445566778899','Business Segment')));
        this.appDetails.push(new AppDetail('Pizza App','90%', 'Updated on Tuesday, 20 Dec 2016', '50', '1234567890',
        new AppProviderDetail('App Provider Name', 'provider@email.com','+445566778899','Business Segment')));
        this.appDetails.push(new AppDetail('Zolo App','90%', 'Updated on Wednesday, 15 Feb 2017', '45', '0987654321',
        new AppProviderDetail('App Provider Name', 'provider@email.com','+445566778899','Business Segment')));
        this.appDetails.push(new AppDetail('Paytm App','90%', 'Updated on Friday, 3 Feb 2017', '12', '1243568790',
        new AppProviderDetail('App Provider Name', 'provider@email.com','+445566778899','Business Segment')));
    }

}
