import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { APP_BASE_HREF } from '@angular/common';

import { DashboardComponent }  from '../components/dashboard/dashboard.component';
import { SidebarNavigationComponent }  from '../components/sidebar-navigation/sidebar-navigation.component';

import { DashboardHomeComponent }  from '../components/dashboard-home/dashboard-home.component';
import { MyCardsComponent }  from '../components/mycards/mycards.component';
import { RequestsComponent }  from '../components/requests/requests.component';
import { AccountComponent }  from '../components/account/account.component';

import { MyCardsTopNavigationComponent }  from '../components/my-cards-top-navigation/my-cards-top-navigation.component';
import { DraftCardsComponent }  from '../components/draft-cards/draft-cards.component';
import { IssuedCardsComponent }  from '../components/issued-cards/issued-cards.component';

import { AddNewCardComponent }  from '../components/add-new-card/add-new-card.component';
import { AddNewCardTopNavigationComponent }  from '../components/add-new-card-top-navigation/add-new-card-top-navigation.component';
import { AddNewCardBasicInformationComponent }  from '../components/add-new-card-basic-information/add-new-card-basic-information.component';

import { CardSnapshotComponent }  from '../components/card-snapshot/card-snapshot.component';
import { ProgressBarComponent }  from '../components/progress-bar/progress-bar.component';
import { ToggleTextComponent }  from '../components/toggle-text/toggle-text.component';
import { DropdownActionComponent }  from '../components/dropdown-action/dropdown-action.component';
import { ApStatusTabulatorComponent }  from '../components/ap-status-tabulator/ap-status-tabulator.component';

import { HttpAuthenticationService }  from '../services/authentication.service';
import { AuthGuard }  from '../services/authguard.service';
import { FlashMessagesModule } from 'angular2flashmessages';

const appRoutes: Routes = [
    { path: 'show-home', component: DashboardHomeComponent },
    { path: 'show-mycards', component: MyCardsComponent, children: [
        { path: 'show-top-nav',  component: MyCardsTopNavigationComponent, children: [
            { path: 'show-draft-cards',  component: DraftCardsComponent },
            { path: 'show-issued-cards',  component: IssuedCardsComponent },
            { path: '**', redirectTo: '/show-mycards/show-issued-cards' },
            { path: '', redirectTo: '/show-mycards/show-issued-cards', pathMatch: 'full' }
        ] },
        { path: 'show-add-new-card', component: AddNewCardComponent, children: [
            { path: 'show-add-new-card-top-nav',  component: AddNewCardTopNavigationComponent, children:[
                { path: 'show-add-new-card-basic-information',  component: AddNewCardBasicInformationComponent },
                { path: '**', redirectTo: '/show-mycards/show-add-new-card/show-add-new-card-top-nav/show-add-new-card-basic-information' },
                { path: '', redirectTo: '/show-mycards/show-add-new-card/show-add-new-card-top-nav/show-add-new-card-basic-information', pathMatch: 'full' }
            ] },
            { path: '**', redirectTo: '/show-mycards/show-add-new-card/show-add-new-card-top-nav/show-add-new-card-basic-information' },
            { path: '', redirectTo: '/show-mycards/show-add-new-card/show-add-new-card-top-nav/show-add-new-card-basic-information', pathMatch: 'full' }
        ] },
        { path: '**', redirectTo: '/show-mycards/show-top-nav/show-issued-cards' },
        { path: '', redirectTo: '/show-mycards/show-top-nav/show-issued-cards', pathMatch: 'full' }
    ] },
    { path: 'show-requests', component: RequestsComponent },
    { path: 'show-account', component: AccountComponent },
    

    // otherwise redirect to Home
    { path: '**', redirectTo: '/show-home' },
    { path: '', redirectTo: '/show-home', pathMatch: 'full' },
];

@NgModule({
  imports: [ BrowserModule, HttpModule, ReactiveFormsModule, RouterModule.forRoot(appRoutes, { useHash : true }), FlashMessagesModule ],
  //exports: [RouterModule],
  providers: [{provide: APP_BASE_HREF, useValue : '/' }, HttpAuthenticationService, AuthGuard],
  declarations: [ DashboardComponent, SidebarNavigationComponent, DashboardHomeComponent, MyCardsComponent, RequestsComponent, AccountComponent, MyCardsTopNavigationComponent, DraftCardsComponent, IssuedCardsComponent, AddNewCardComponent, AddNewCardTopNavigationComponent, AddNewCardBasicInformationComponent, CardSnapshotComponent, ProgressBarComponent, ToggleTextComponent, DropdownActionComponent, ApStatusTabulatorComponent ],
  bootstrap:    [ DashboardComponent ]
})

export class AppModule { }
