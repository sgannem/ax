import { Component, OnInit, Host } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';

import { Select2OptionData } from 'ng2-select2/ng2-select2';
import { AddNewAppNavigationComponent } from '../add-new-app-navigation/add-new-app-navigation.component';

 let  $:JQueryStatic;

@Component({
    moduleId: module.id,
    selector: 'show-add-new-app-basic-information',
    templateUrl: 'add-new-app-basic-info.component.html',
    styleUrls: ['add-new-app-basic-info.component.scss']
})
export class AddNewAppBasicInformationComponent implements OnInit{

    private _master: AddNewAppNavigationComponent;
    public businessSegmentsList: Array<Select2OptionData>;
    public MyAppBasicInfoForm: FormGroup;
    providerName: string;
    businessSegmentValue: string;
    countriesList: Array<Select2OptionData>;
    countryOptions:any;
    countryValue: string[];


    constructor(private _fb: FormBuilder, @Host() _master: AddNewAppNavigationComponent) {
        this._master = _master;
    }

   ngOnInit() {

        this.MyAppBasicInfoForm = this._fb.group({
            providerName: [""],
            email:[""],
            phoneNumber:[""],
            applicationUsed:[""]
        });
       
        this.businessSegmentsList = [
            {id:'', text:'Choose a business segment'},
            {id:'1', text:'Airlines'},
            {id:'2', text:'Amusement and entertainment'},
            {id:'3', text:'Associations'}
        ];

        this.countriesList = [
            {id:'', text:'Choose a business segment'},
            {id:'1', text:'Airlines'},
            {id:'2', text:'Amusement and entertainment'},
            {id:'3', text:'Associations'}
        ];

       this.countryOptions = {
      multiple: true
    };
        this.reloadDataFromParent();

        setTimeout("$('#cardLogo').fileinput({'browseClass': 'fileUploadBtn', 'browseLabel': 'Choose file ...', 'showCaption': false, 'showPreview': true, 'showUpload': true, 'showClose': false, 'showRemove': false, 'showCancel': false, 'previewFileType':'image'})", 100);
        setTimeout("$('.kv-upload-progress').hide()", 200);
        setTimeout("$('.select2-selection--single').addClass('add-light-gray-background')", 10);
    }

    public updateBasicInfoToMaster(e: any): void {
        this.updateBasicInfoToParent();
    }

    public updateBasicInfoToParent()
    {
        this._master.myAppBasicInformationModel.setProviderName(this.MyAppBasicInfoForm.get("providerName").value);
        this._master.myAppBasicInformationModel.setEmailId(this.MyAppBasicInfoForm.get("email").value);
        this._master.myAppBasicInformationModel.setPhoneNumber(this.MyAppBasicInfoForm.get("phoneNumber").value);
        this._master.myAppBasicInformationModel.setApplicationUsed(this.MyAppBasicInfoForm.get("applicationUsed").value);
    }

 selectBusinessSegment(data: {value: string}) {
        this.businessSegmentValue = data.value;
        this._master.myAppBasicInformationModel.setBussinessSegment(this.businessSegmentValue);
  }

   selectCountry(data: {value: string[]}) {
       this.countryValue = data.value;
       this._master.myAppBasicInformationModel.setCountries(this.countryValue);
  }

    reloadDataFromParent()
    {
        this.MyAppBasicInfoForm.get("providerName").setValue(this._master.myAppBasicInformationModel.getProviderName());
        this.MyAppBasicInfoForm.get("email").setValue(this._master.myAppBasicInformationModel.getEmailId());
        this.MyAppBasicInfoForm.get("phoneNumber").setValue(this._master.myAppBasicInformationModel.getPhoneNumber());
        this.MyAppBasicInfoForm.get("applicationUsed").setValue(this._master.myAppBasicInformationModel.getApplicationUsed());
        this.businessSegmentValue = this._master.myAppBasicInformationModel.getBussinessSegment();
        this.countryValue = this._master.myAppBasicInformationModel.getCountries();
    }
    
    ngOnDestroy() {
        this.updateBasicInfoToParent();
    }
}