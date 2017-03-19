// Angular Imports
import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { APP_BASE_HREF } from '@angular/common';

// This Module's Components
import { AccountTopNavigationComponent } from './account-top-navigation.component';
import { AccountDetailsComponent }  from '../accountdetails/accountdetails.component';
import { TermsAndConditionsComponent }  from '../termsandconditions/termsandconditions.component';
import { PricingTiersComponent }  from '../pricingtiers/pricingtiers.component';
import { KeySettingsComponent } from '../keysettings/keysettings.component';

@NgModule({
    imports: [ BrowserModule, HttpModule, ReactiveFormsModule ],
    providers: [{provide: APP_BASE_HREF, useValue : '/' }],
    declarations: [ AccountTopNavigationComponent, AccountDetailsComponent, TermsAndConditionsComponent, PricingTiersComponent, KeySettingsComponent  ],
    exports: [ AccountTopNavigationComponent ]
})

export class MyCardsTopNavigationModule {

}
