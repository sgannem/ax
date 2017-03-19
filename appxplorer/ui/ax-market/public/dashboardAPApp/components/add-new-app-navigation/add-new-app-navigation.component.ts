import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { MyAppBasicInformationModel,MyAppMarketingInformationModel } from '../../models/my-app-basic-info.model';

@Component({
    moduleId: module.id,
    selector: 'show-add-new-app-navigation',
    templateUrl: 'add-new-app-navigation.component.html',
    styleUrls: ['add-new-app-navigation.component.scss']
})

export class AddNewAppNavigationComponent {

    myAppBasicInformationModel:MyAppBasicInformationModel;
    myAppMarketingInformationModel: MyAppMarketingInformationModel;

    isFirstPage: boolean = true;
    isLastPage: boolean = false;
    
    prevPage: number = 0;
    nextPage: number = 1;


    routerLinks: string[] = ['/show-myapps/show-add-new-app/show-add-new-app-nav/show-add-new-app-basic-information',
                            '/show-myapps/show-add-new-app/show-add-new-app-nav/show-add-new-app-marketing',
                            '/show-myapps/show-add-new-app/show-add-new-app-nav/show-add-new-app-technical-details',
                            '/show-myapps/show-add-new-app/show-add-new-app-nav/show-add-new-app-confirm'];

    constructor(private _router: Router) {
        this.myAppBasicInformationModel = new MyAppBasicInformationModel();
        this.myAppMarketingInformationModel = new MyAppMarketingInformationModel();
    }

    navigateToPage(pageIndex: number) {

        if(this.prevPage<0) {
            this.prevPage = 0;
        }

        if(this.nextPage>=this.routerLinks.length) {
            this.nextPage = this.routerLinks.length - 1;
        }

        this.prevPage = pageIndex-1;
        this.nextPage = pageIndex+1;

        if(pageIndex<=0) {
            this.isFirstPage = true;
            this.isLastPage = false;
        }

        if(pageIndex>0) {
            this.isFirstPage = false;
            this.isLastPage = false;
        }

        if(pageIndex>=(this.routerLinks.length-1)) {
            this.isLastPage = true;
        }
        
        this._router.navigateByUrl('/show-myapps/show-add-new-app/show-add-new-app-nav/');

    }

    saveApplication()
    {
        alert(this.myAppBasicInformationModel.getProviderName());
    }
}
