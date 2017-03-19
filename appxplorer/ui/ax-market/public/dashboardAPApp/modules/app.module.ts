import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { APP_BASE_HREF } from '@angular/common';


import { DashboardComponent } from '../components/dashboard/dashboard.component';
import { SidebarNavigationComponent } from '../components/sidebar-navigation/sidebar-navigation.component';
import { TopNavigationComponent } from '../components/top-navigation/top-navigation.component';
import { BottomNavigationComponent } from '../components/bottom-navigation/bottom-navigation.component';


import { MyAppsComponent } from '../components/myapps/myapps.component';
import { RequestsComponent } from '../components/requests/requests.component';

import { AccountComponent } from '../components/account/account.component';
import { AccountTopNavigationComponent } from '../components/account-top-navigation/account-top-navigation.component';
import { AccountDetailsComponent } from '../components/accountdetails/accountdetails.component';
import { PaymentsComponent } from '../components/payments/payments.component';
import { PromotionsComponent } from '../components/promotions/promotions.component';
import { TermsAndConditionsComponent } from '../components/termsandconditions/termsandconditions.component';
import { KeySettingsComponent } from '../components/keysettings/keysettings.component';
import { BillingComponent } from '../components/billing/billing.component';

import { HttpAuthenticationService } from '../services/authentication.service';
import { AuthGuard } from '../services/authguard.service';


import { DashboardHomeComponent } from '../components/dashboardhome/dashboardhome.component';
import { DashboardHomeTopNavigationComponent } from '../components/dashboardhome-topnavigation/dashboardhome-topnavigation.component';
import { DashboardHomeOverViewComponent } from '../components/dashboardhome-overview/dashboardhome-overview.component';

import { AppSnapshotComponent } from '../components/app-snapshot/app-snapshot.component';
import { CardSnapshotComponent } from '../components/card-snapshot/card-snapshot.component';
import { ProgressBarComponent } from '../components/progress-bar/progress-bar.component';
import { ToggleTextComponent } from '../components/toggle-text/toggle-text.component';
import { DropdownActionComponent } from '../components/dropdown-action/dropdown-action.component';
import { ApStatusTabulatorComponent } from '../components/ap-status-tabulator/ap-status-tabulator.component';

import { MyAppsTopNavigationComponent } from '../components/my-apps-top-navigation/my-apps-top-navigation.component';
import { PublishedAppsComponent } from '../components/published-apps/published-apps.component';
import { DraftAppsComponent } from '../components/draft-apps/draft-apps.component';
import { AddNewAppComponent } from '../components/add-new-app/add-new-app.component';
import { AddNewAppNavigationComponent } from '../components/add-new-app-navigation/add-new-app-navigation.component';
import { AddNewAppBasicInformationComponent } from '../components/add-new-app-basic-info/add-new-app-basic-info.component';
import { AddNewAppMarketingComponent } from '../components/add-new-app-marketing/add-new-app-marketing.component';
import { AddNewAppTechnicalDetailsComponent } from '../components/add-new-app-technical/add-new-app-technical.component';
import { AddNewAppConfirmComponent } from '../components/add-new-app-confirmation/add-new-app-confirmation.component';

import { FeaturedCIService } from '../services/featuredCI.service';
import { LoginComponent } from '../components/login/login.component';
import { Ng2Bs3ModalModule } from 'ng2-bs3-modal/ng2-bs3-modal';
import { LoginParentComponent } from '../components/login-parent/login-parent.component';

import { FlashMessagesModule } from 'angular2-flash-messages';
import { Select2Module } from 'ng2-select2/ng2-select2';
import { PdfViewerComponent } from 'ng2-pdf-viewer';



import { RequestsTopNavigationComponent } from '../components/requests-top-navigation/requests-top-navigation.component';
import { PendingrequestsComponent } from '../components/pendingrequests/pendingrequests.component';
import { RequestListComponent } from '../components/request-list/request-list.component';
import { RequestDetailComponent } from '../components/request-detail/request-detail.component';
import { CreateNewRequestComponent } from '../components/create-new-request/create-new-request.component';

import { AppDescComponent } from '../components/app-desc/app-desc.component';
import { AppDescNavComponent } from '../components/app-desc-nav/app-desc-nav.component';
import { AppNameComponent } from '../components/app-name/app-name.component';

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


  { path: 'show-myapps', component: MyAppsComponent, canActivate: [AuthGuard], children:[
{
                path: 'show-myapp-top-nav', component: MyAppsTopNavigationComponent 
                ,children: [
                    { path: 'show-published-apps', component: PublishedAppsComponent },
                    { path: 'show-draft-apps', component: DraftAppsComponent },
                    { path: '**', redirectTo: '/show-myapps/show-myapp-top-nav/show-published-apps' },
                    { path: '', redirectTo: '/show-myapps/show-myapp-top-nav/show-published-apps', pathMatch: 'full' }
                ]
            },

            {
                path: 'show-add-new-app', component: AddNewAppComponent, children: [
                    {
                        path: 'show-add-new-app-nav', component: AddNewAppNavigationComponent, children: [
                            { path: 'show-add-new-app-basic-information', component: AddNewAppBasicInformationComponent },
                            { path: 'show-add-new-app-marketing', component: AddNewAppMarketingComponent },
                            { path: 'show-add-new-app-technical-details', component: AddNewAppTechnicalDetailsComponent },
                            { path: 'show-add-new-app-confirm', component: AddNewAppConfirmComponent },
                            { path: '**', redirectTo: '/show-myapps/show-add-new-app/show-add-new-app-nav/show-add-new-app-basic-information' },
                            { path: '', redirectTo: '/show-myapps/show-add-new-app/show-add-new-app-nav/show-add-new-app-basic-information', pathMatch: 'full' }
                        ]
                    },
                    { path: '**', redirectTo: '/show-myapps/show-add-new-app/show-add-new-app-nav/show-add-new-app-basic-information' },
                    { path: '', redirectTo: '/show-myapps/show-add-new-app/show-add-new-app-nav/show-add-new-app-basic-information', pathMatch: 'full' }
                ]
            },

{
                path: 'show-app-desc', component: AppDescComponent, children: [
                    {
                        path: 'show-app-desc-nav', component: AppDescNavComponent, children: [
                            { path: 'show-app-name', component: AppNameComponent },
                            
                            { path: '**', redirectTo: '/show-myapps/show-app-desc/show-app-desc-nav/show-app-name' },
                            { path: '', redirectTo: '/show-myapps/show-app-desc/show-app-desc-nav/show-app-name', pathMatch: 'full' }
                        ]
                    },
                    { path: '**', redirectTo: '/show-myapps/show-app-desc/show-app-desc-nav/show-app-name' },
                            { path: '', redirectTo: '/show-myapps/show-app-desc/show-app-desc-nav/show-app-name', pathMatch: 'full' }
                ]
            },

            { path: '**', redirectTo: '/show-myapps/show-myapp-top-nav/show-published-apps' },
            { path: '', redirectTo: '/show-myapps/show-myapp-top-nav/show-published-apps', pathMatch: 'full' }
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
          { path: 'show-payments', component: PaymentsComponent },
          { path: 'show-promotions', component: PromotionsComponent },
          { path: 'show-termsandconditions', component: TermsAndConditionsComponent },
          { path: 'show-keysettings', component: KeySettingsComponent },
          { path: 'show-billing', component: BillingComponent },
          { path: '**', redirectTo: '/show-account/show-account-top-nav/show-accountdetails' },
          { path: '', redirectTo: '/show-account/show-account-top-nav/show-accountdetails', pathMatch: 'full' }
        ]
      },
      { path: '**', redirectTo: '/show-account/show-account-top-nav/show-accountdetails' },
      { path: '', redirectTo: '/show-account/show-account-top-nav/show-accountdetails', pathMatch: 'full' }
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'login-parent', component: LoginParentComponent },

  // otherwise redirect to Home
  // { path: '**', redirectTo: '/show-home' },
  // { path: '', redirectTo: '/show-home', pathMatch: 'full' },
  { path: '**', redirectTo: '/show-home/show-top-nav/show-overview' },
  { path: '', redirectTo: '/show-home/show-top-nav/show-overview', pathMatch: 'full' }


];

@NgModule({
  imports: [BrowserModule, HttpModule, ReactiveFormsModule, FormsModule,
    RouterModule.forRoot(appRoutes, { useHash: true }), Select2Module,
    Ng2Bs3ModalModule, FlashMessagesModule],
  //exports: [RouterModule],

  providers: [{ provide: APP_BASE_HREF, useValue: '/' }, HttpAuthenticationService, AuthGuard],
  declarations: [LoginParentComponent, LoginComponent, DashboardComponent, SidebarNavigationComponent,
    TopNavigationComponent, BottomNavigationComponent, AccountTopNavigationComponent, AccountDetailsComponent, PromotionsComponent,
    TermsAndConditionsComponent, KeySettingsComponent, PaymentsComponent, BillingComponent,
    DashboardHomeComponent, DashboardHomeTopNavigationComponent, DashboardHomeOverViewComponent,

    MyAppsComponent, RequestsComponent, AccountComponent, AppSnapshotComponent, ProgressBarComponent,
    ToggleTextComponent, DropdownActionComponent, ApStatusTabulatorComponent, CardSnapshotComponent,
    MyAppsTopNavigationComponent, PublishedAppsComponent, DraftAppsComponent, 
    AddNewAppComponent, AddNewAppNavigationComponent, AddNewAppBasicInformationComponent, 
    AddNewAppTechnicalDetailsComponent, AddNewAppMarketingComponent,AddNewAppConfirmComponent,
    RequestsTopNavigationComponent, PendingrequestsComponent, RequestListComponent, RequestDetailComponent, CreateNewRequestComponent,
    AppDescComponent,AppDescNavComponent,AppNameComponent, PdfViewerComponent],

  bootstrap: [DashboardComponent]

})

export class AppModule { }
