import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { APP_BASE_HREF } from '@angular/common';

import { DashboardComponent } from '../components/dashboard/dashboard.component';
import { SidebarNavigationComponent } from '../components/sidebar-navigation/sidebar-navigation.component';

import { DashboardHomeComponent } from '../components/dashboardhome/dashboardhome.component';
import { DashboardHomeTopNavigationComponent } from '../components/dashboardhome-topnavigation/dashboardhome-topnavigation.component';
import { DashboardHomeOverViewComponent } from '../components/dashboardhome-overview/dashboardhome-overview.component';
import { MyCardsComponent } from '../components/mycards/mycards.component';
import { RequestsComponent } from '../components/requests/requests.component';
// import { AccountComponent }  from '../components/account/account.component';

import { MyCardsTopNavigationComponent } from '../components/my-cards-top-navigation/my-cards-top-navigation.component';
import { DraftCardsComponent } from '../components/draft-cards/draft-cards.component';
import { IssuedCardsComponent } from '../components/issued-cards/issued-cards.component';

import { AddNewCardComponent } from '../components/add-new-card/add-new-card.component';
import { AddNewCardNavigationComponent } from '../components/add-new-card-navigation/add-new-card-navigation.component';
import { AddNewCardBasicInformationComponent } from '../components/add-new-card-basic-information/add-new-card-basic-information.component';
import { AddNewCardTechnicalDetailsComponent } from '../components/add-new-card-technical-details/add-new-card-technical-details.component';
import { AddNewCardPartitionMediumComponent } from '../components/add-new-card-partition-medium/add-new-card-partition-medium.component';
import { AddNewCardConfirmComponent } from '../components/add-new-card-confirm/add-new-card-confirm.component';

import { CardSnapshotComponent } from '../components/card-snapshot/card-snapshot.component';
import { ProgressBarComponent } from '../components/progress-bar/progress-bar.component';
import { ToggleTextComponent } from '../components/toggle-text/toggle-text.component';
import { DropdownActionComponent } from '../components/dropdown-action/dropdown-action.component';
import { ApStatusTabulatorComponent } from '../components/ap-status-tabulator/ap-status-tabulator.component';
import { PartitionMediumSettingsComponent } from '../components/partition-medium-settings/partition-medium-settings.component';
import { PartitionMediumCardLayoutComponent } from '../components/partition-medium-card-layout/partition-medium-card-layout.component';

import { AccountTopNavigationComponent } from '../components/account-top-navigation/account-top-navigation.component';
import { AccountComponent } from '../components/account/account.component';
import { AccountDetailsComponent } from '../components/accountdetails/accountdetails.component';
import { TermsAndConditionsComponent } from '../components/termsandconditions/termsandconditions.component';
import { PricingTiersComponent } from '../components/pricingtiers/pricingtiers.component';
import { KeySettingsComponent } from '../components/keysettings/keysettings.component';
import { PartitionMediumSlotWidgetComponent } from '../components/partition-medium-slot-widget/partition-medium-slot-widget.component';

import { RequestsTopNavigationComponent } from '../components/requests-top-navigation/requests-top-navigation.component';
import { PendingrequestsComponent } from '../components/pendingrequests/pendingrequests.component';
import { RequestListComponent } from '../components/request-list/request-list.component';
import { RequestDetailComponent } from '../components/request-detail/request-detail.component';
import { CreateNewRequestComponent } from '../components/create-new-request/create-new-request.component';

//import { AcceptedrequestsComponent } from '../components/acceptedrequests/acceptedrequests.component';
//import { DeclinedrequestsComponent } from '../components/declinedrequests/declinedrequests.component';


import { HttpAuthenticationService } from '../services/authentication.service';
import { AuthGuard } from '../services/authguard.service';

import { LoginComponent } from '../components/login/login.component';
import { LoginParentComponent } from '../components/login-parent/login-parent.component';
import { Ng2Bs3ModalModule } from 'ng2-bs3-modal/ng2-bs3-modal';
import { FlashMessagesModule } from 'angular2-flash-messages';
import { Select2Module } from 'ng2-select2/ng2-select2';
import { PdfViewerComponent } from 'ng2-pdf-viewer';

const appRoutes: Routes = [
    {
        path: 'show-home', component: DashboardHomeComponent, canActivate: [AuthGuard], children: [
            {
                path: 'show-top-nav', component: DashboardHomeTopNavigationComponent, children: [
                    { path: 'show-overview', component: DashboardHomeOverViewComponent },
                    { path: '**', redirectTo: '/show-home/show-top-nav/show-overview' },
                    { path: '', redirectTo: '/show-home/show-top-nav/show-overview', pathMatch: 'full' }
                ]
            },
            { path: '**', redirectTo: '/show-home/show-top-nav/show-overview' },
            { path: '', redirectTo: '/show-home/show-top-nav/show-overview', pathMatch: 'full' }
        ]
    },
    {
        path: 'show-mycards', component: MyCardsComponent, canActivate: [AuthGuard], children: [
            {
                path: 'show-top-nav', component: MyCardsTopNavigationComponent, children: [
                    { path: 'show-draft-cards', component: DraftCardsComponent },
                    { path: 'show-issued-cards', component: IssuedCardsComponent },                                        
                    { path: '**', redirectTo: '/show-mycards/show-issued-cards' },
                    { path: '', redirectTo: '/show-mycards/show-issued-cards', pathMatch: 'full' }
                ]
            },

            {
                path: 'show-add-new-card', component: AddNewCardComponent, children: [
                    {
                        path: 'show-add-new-card-nav', component: AddNewCardNavigationComponent, children: [
                            { path: 'show-add-new-card-basic-information', component: AddNewCardBasicInformationComponent },
                            { path: 'show-add-new-card-technical-details', component: AddNewCardTechnicalDetailsComponent },
                            { path: 'show-add-new-card-partition-medium', component: AddNewCardPartitionMediumComponent },
                            { path: 'show-add-new-card-confirm', component: AddNewCardConfirmComponent },
                            { path: '**', redirectTo: '/show-mycards/show-add-new-card/show-add-new-card-nav/show-add-new-card-basic-information' },
                            { path: '', redirectTo: '/show-mycards/show-add-new-card/show-add-new-card-nav/show-add-new-card-basic-information', pathMatch: 'full' }
                        ]
                    },
                    { path: '**', redirectTo: '/show-mycards/show-add-new-card/show-add-new-card-nav/show-add-new-card-basic-information' },
                    { path: '', redirectTo: '/show-mycards/show-add-new-card/show-add-new-card-nav/show-add-new-card-basic-information', pathMatch: 'full' }
                ]
            },
            { path: '**', redirectTo: '/show-mycards/show-top-nav/show-issued-cards' },
            { path: '', redirectTo: '/show-mycards/show-top-nav/show-issued-cards', pathMatch: 'full' }
        ]
    },
    { path: 'show-requests', component: RequestsComponent, canActivate: [AuthGuard], children: [
            {
                path: 'show-requests-top-nav', component: RequestsTopNavigationComponent, children: [
                    { path: 'show-pendingrequests', component: PendingrequestsComponent },
                     { path: 'show-create-new-request', component: CreateNewRequestComponent },
                    { path: '**', redirectTo: '/show-requests/show-requests-top-nav/show-pendingrequests?requestStatus=Pending' },
                    { path: '', redirectTo: '/show-requests/show-requests-top-nav/show-pendingrequests?requestStatus=Pending', pathMatch: 'full' }
                ]
            },
             { path: '**', redirectTo: '/show-requests/show-requests-top-nav/show-pendingrequests?requestStatus=Pending' },
             { path: '', redirectTo: '/show-requests/show-requests-top-nav/show-pendingrequests?requestStatus=Pending', pathMatch: 'full' }
        ] 
    },
    {
        path: 'show-account', component: AccountComponent, canActivate: [AuthGuard], children: [
            {
                path: 'show-account-top-nav', component: AccountTopNavigationComponent, children: [
                    { path: 'show-accountdetails', component: AccountDetailsComponent },
                    { path: 'show-termsandconditions', component: TermsAndConditionsComponent },
                    { path: 'show-pricingtiers', component: PricingTiersComponent },
                    { path: 'show-keysettings', component: KeySettingsComponent },
                    { path: '**', redirectTo: '/show-account/show-account-top-nav/show-accountdetails' },
                    { path: '', redirectTo: '/show-account/show-account-top-nav/show-accountdetails', pathMatch: 'full' }
                ]
            },
            { path: '**', redirectTo: '/show-account/show-account-top-nav/show-accountdetails' },
            { path: '', redirectTo: '/show-account/show-account-top-nav/show-accountdetails', pathMatch: 'full' }
        ]
    },
    { path: 'login-parent', component: LoginParentComponent },

    // otherwise redirect to Home
    { path: '**', redirectTo: '/show-home/show-top-nav/show-overview' },
    { path: '', redirectTo: '/show-home/show-top-nav/show-overview', pathMatch: 'full' },
];

@NgModule({
    imports: [BrowserModule, HttpModule, FormsModule, ReactiveFormsModule, RouterModule.forRoot(appRoutes, { useHash: true }), Ng2Bs3ModalModule, FlashMessagesModule, Select2Module],
    
    providers: [{ provide: APP_BASE_HREF, useValue: '/' }, HttpAuthenticationService, AuthGuard],
    
    declarations: [LoginParentComponent, LoginComponent, DashboardComponent, SidebarNavigationComponent, DashboardHomeComponent, MyCardsComponent, RequestsComponent, MyCardsTopNavigationComponent, DraftCardsComponent, IssuedCardsComponent, AddNewCardComponent, AddNewCardNavigationComponent, AddNewCardBasicInformationComponent, AddNewCardTechnicalDetailsComponent, AddNewCardPartitionMediumComponent, AddNewCardConfirmComponent, CardSnapshotComponent, ProgressBarComponent, ToggleTextComponent, DropdownActionComponent, ApStatusTabulatorComponent, AccountTopNavigationComponent, AccountComponent, AccountDetailsComponent, TermsAndConditionsComponent, PricingTiersComponent, KeySettingsComponent, 
    DashboardHomeComponent, DashboardHomeTopNavigationComponent, DashboardHomeOverViewComponent, PartitionMediumSettingsComponent, PartitionMediumCardLayoutComponent, PartitionMediumSlotWidgetComponent,RequestsTopNavigationComponent, PendingrequestsComponent, RequestListComponent, RequestDetailComponent, CreateNewRequestComponent, PdfViewerComponent],
    bootstrap: [DashboardComponent]
})

export class AppModule { }
