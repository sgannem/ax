import { Component } from '@angular/core';

import { CardDetail } from './CardDetail';

@Component({
    moduleId: module.id,
    selector: 'issued-cards',
    templateUrl: 'issued-cards.component.html',
    styleUrls: ['issued-cards.component.scss']
})

export class IssuedCardsComponent {

    public cardDetails: CardDetail[] = [];

    constructor() {
        this.cardDetails.push(new CardDetail('Bengaluru Bus Pass', 'Updated on Friday, 20 Jan 2017', '30', '344343353535'));
        this.cardDetails.push(new CardDetail('London Bus Pass', 'Updated on Tuesday, 20 Dec 2016', '50', '1234567890'));
        this.cardDetails.push(new CardDetail('Austria Tourist Card', 'Updated on Wednesday, 15 Feb 2017', '45', '0987654321'));
        this.cardDetails.push(new CardDetail('German Tourist Card', 'Updated on Friday, 3 Feb 2017', '12', '1243568790'));
    }
}
