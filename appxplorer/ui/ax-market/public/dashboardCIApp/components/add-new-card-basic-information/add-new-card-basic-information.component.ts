import { Component, OnInit } from '@angular/core';

import { Select2OptionData } from 'ng2-select2/ng2-select2';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import {MyCardService} from '../../services/my-card.service';
import {HTTPHeader} from '../../commons/httpheader';


@Component({
    moduleId: module.id,
    selector: 'add-new-card-basic-information',
    templateUrl: 'add-new-card-basic-information.component.html',
    styleUrls: ['add-new-card-basic-information.component.scss'],
    providers: [MyCardService,HTTPHeader]
})

export class AddNewCardBasicInformationComponent implements OnInit {

    $:JQueryStatic;
    public businessSegments: Array<Select2OptionData>;
    public countriesList: Array<Select2OptionData>;
    public options: Select2Options;
    public value: string[];
    countryOptions:any;

    constructor(private _fb: FormBuilder, private router: Router, private myCardService: MyCardService) {

    }

    ngOnInit() {
    
        this.businessSegments = this.myCardService.getBussinessSegmentList().bussinessSegmentResponse;
        this.countriesList=this.myCardService.getCountryList().countryListResponse;


        //this.businessSegments.push()
/*
 this.countriesList = [
            {id:'0', text:'India'},
            {id:'1', text:'USA'},
            {id:'2', text:'Europe'},
            {id:'3', text:'Canada'}
    ];


this.countriesList = [
            {"id":"0", "text":"Choose a business segment"},
            {"id":"1", "text":"Airlines"},
            {"id":"2", "text":"Amusement and entertainment"},
            {"id":"3", "text":"Associations"},
            {"id":"4", "text":"India"}
        ];
*/
     this.countryOptions = {
      multiple: true
    }

        setTimeout("$('#cardLogo').fileinput({'browseClass': 'fileUploadBtn', 'browseLabel': 'Choose file ...', 'showCaption': false, 'showPreview': true, 'showUpload': true, 'showClose': false, 'showRemove': false, 'showCancel': false, 'previewFileType':'image'})", 100);
        setTimeout("$('.kv-upload-progress').hide()", 200);
        setTimeout("$('.select2-selection--single').addClass('add-light-gray-background')", 10);
        
    }

    selectCountry(data: {value: string[]}) {
   alert(data.value);
  }

}