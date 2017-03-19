import { Component, OnInit, Host } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';

import { AddNewAppNavigationComponent } from '../add-new-app-navigation/add-new-app-navigation.component';

 let  $:JQueryStatic;

@Component({
    moduleId: module.id,
    selector: 'show-add-new-app-marketing',
    templateUrl: 'add-new-app-marketing.component.html',
    styleUrls: ['add-new-app-marketing.component.scss']
})


export class AddNewAppMarketingComponent implements OnInit{

    private _master: AddNewAppNavigationComponent;
    public myAppMarketingForm: FormGroup;


 constructor(private _fb: FormBuilder, @Host() _master: AddNewAppNavigationComponent) {
        this._master = _master;
    }

   ngOnInit() {

 this.myAppMarketingForm = this._fb.group({
            applicationName: [""],
            shortDescription:[""],
            detailedDescription:[""],
            applicationWebsite:[""],
            preferredType:[""]
        });

        this.reloadMarketingDataFromParent();
        setTimeout("$('#appLogo').fileinput({'browseClass': 'fileUploadBtn', 'browseLabel': 'Choose file ...', 'showCaption': false, 'showPreview': true, 'showUpload': true, 'showClose': false, 'showRemove': false, 'showCancel': false, 'previewFileType':'image'})", 100);
        setTimeout("$('.kv-upload-progress').hide()", 200);
        setTimeout("$('.select2-selection--single').addClass('add-light-gray-background')", 10);
   }

    public updateMarketingInfoToMaster(e: any): void {
        this.updateMarketingInfoToParent();
    }

    public updateMarketingInfoToParent()
    {
        this._master.myAppMarketingInformationModel.setApplicationName(this.myAppMarketingForm.get("applicationName").value);
        this._master.myAppMarketingInformationModel.setShortDescription(this.myAppMarketingForm.get("shortDescription").value);
        this._master.myAppMarketingInformationModel.setDetailedDescription(this.myAppMarketingForm.get("detailedDescription").value);
        this._master.myAppMarketingInformationModel.setApplicationWebsite(this.myAppMarketingForm.get("applicationWebsite").value);
    }

   reloadMarketingDataFromParent()
    {
        this.myAppMarketingForm.get("applicationName").setValue(this._master.myAppMarketingInformationModel.getApplicationName());
        this.myAppMarketingForm.get("shortDescription").setValue(this._master.myAppMarketingInformationModel.getShortDescription());
        this.myAppMarketingForm.get("detailedDescription").setValue(this._master.myAppMarketingInformationModel.getDetailedDescription());
        this.myAppMarketingForm.get("applicationWebsite").setValue(this._master.myAppMarketingInformationModel.getApplicationWebsite());
    }

    ngOnDestroy() {
        this.updateMarketingInfoToParent();
    }
}


