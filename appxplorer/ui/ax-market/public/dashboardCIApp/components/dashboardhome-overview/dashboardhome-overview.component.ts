import { Component } from '@angular/core';
// import { FeaturedCIService } from '../../services/featuredCI.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CardDetail } from './CardDetail';

@Component({
    moduleId: module.id,
    selector: 'dashboard-home',
    templateUrl: 'dashboardhome-overview.component.html',
    styleUrls: ['dashboardhome-overview.component.scss'],
    providers: []
})
export class DashboardHomeOverViewComponent {
public cardDetails: CardDetail[] = [];

    constructor() {
        this.cardDetails.push(new CardDetail('Bengaluru Bus Pass', 'Updated on Friday, 20 Jan 2017', '30', '344343353535'));
        this.cardDetails.push(new CardDetail('London Bus Pass', 'Updated on Tuesday, 20 Dec 2016', '50', '1234567890'));
        this.cardDetails.push(new CardDetail('Austria Tourist Card', 'Updated on Wednesday, 15 Feb 2017', '45', '0987654321'));
        this.cardDetails.push(new CardDetail('German Tourist Card', 'Updated on Friday, 3 Feb 2017', '12', '1243568790'));
        this.cardDetails.push(new CardDetail('Indian Tourist Card', 'Updated on Friday, 3 Feb 2017', '12', '1243568790'));
        this.cardDetails.push(new CardDetail('Sri Lanka Tourist Card', 'Updated on Friday, 3 Feb 2017', '12', '1243568790'));
        this.cardDetails.push(new CardDetail('Swiss Tourist Card', 'Updated on Friday, 3 Feb 2017', '12', '1243568790'));
    }
}
