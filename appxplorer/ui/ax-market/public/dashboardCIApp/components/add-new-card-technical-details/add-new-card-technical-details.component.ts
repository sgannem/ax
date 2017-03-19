import { Component, OnInit } from '@angular/core';

import {MyCardService} from '../../services/my-card.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import {HTTPHeader} from '../../commons/httpheader';

import {LookupResponseDto, LookUp} from '../../responses/lookup.response';


@Component({
    moduleId: module.id,
    selector: 'add-new-card-technical-details',
    templateUrl: 'add-new-card-technical-details.component.html',
    styleUrls: ['add-new-card-technical-details.component.scss'],
    providers: [MyCardService,HTTPHeader]
})
export class AddNewCardTechnicalDetailsComponent implements OnInit{

smartMediumTypeList: Array<LookUp>;
technologyList: Array<LookUp>;
smartMediumSizeList: Array<LookUp>;
lookupResponseDto: LookupResponseDto;
  constructor(private _fb: FormBuilder, private router: Router, private myCardService: MyCardService) { }

 ngOnInit() {
    this.smartMediumTypeList = this.myCardService.getSmartMediumTypeList().lookupResponse;
    this.technologyList = this.myCardService.getTechnologyList().lookupResponse;
    this.smartMediumSizeList = this.myCardService.getSmartMediumSizeList().lookupResponse;
}


}

