// Angular Imports
import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { APP_BASE_HREF } from '@angular/common';

// This Module's Components
import { MyAppsTopNavigationComponent } from './my-apps-top-navigation.component';
import { DraftCardsComponent }  from '../draft-cards/draft-cards.component';
import { IssuedCardsComponent }  from '../issued-cards/issued-cards.component';

@NgModule({
    imports: [ BrowserModule, HttpModule, ReactiveFormsModule ],
    providers: [{provide: APP_BASE_HREF, useValue : '/' }],
    declarations: [ MyAppsTopNavigationComponent, DraftCardsComponent, IssuedCardsComponent ],
    exports: [ MyAppsTopNavigationComponent ]
})

export class MyAppsTopNavigationModule {

}
