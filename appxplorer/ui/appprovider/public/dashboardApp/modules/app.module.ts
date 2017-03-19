import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { APP_BASE_HREF } from '@angular/common';

import { DashboardComponent }  from '../components/dashboard/dashboard.component';
import { SidebarNavigationComponent }  from '../components/sidebar-navigation/sidebar-navigation.component';
import { TopNavigationComponent }  from '../components/top-navigation/top-navigation.component';
import { BottomNavigationComponent }  from '../components/bottom-navigation/bottom-navigation.component';

import { DashboardHomeComponent }  from '../components/dashboard-home/dashboard-home.component';
import { MyAppsComponent }  from '../components/myapps/myapps.component';
import { RequestsComponent }  from '../components/requests/requests.component';
import { AccountComponent }  from '../components/account/account.component';

import { HttpAuthenticationService }  from '../services/authentication.service';
import { AuthGuard }  from '../services/authguard.service';

const appRoutes: Routes = [
  
    { path: 'show-home', component: DashboardHomeComponent },
    { path: 'show-myapps', component: MyAppsComponent },
    { path: 'show-requests', component: RequestsComponent },
    { path: 'show-account', component: AccountComponent },

    // otherwise redirect to Home
    { path: '**', redirectTo: '/show-home' },
    { path: '', redirectTo: '/show-home', pathMatch: 'full' },
];

@NgModule({
  imports: [ BrowserModule, HttpModule, ReactiveFormsModule, RouterModule.forRoot(appRoutes, { useHash : true }) ],
  //exports: [RouterModule],
  providers: [{provide: APP_BASE_HREF, useValue : '/' }, HttpAuthenticationService, AuthGuard],
  declarations: [ DashboardComponent, SidebarNavigationComponent, TopNavigationComponent, BottomNavigationComponent, DashboardHomeComponent, MyAppsComponent, RequestsComponent, AccountComponent ],
  bootstrap:    [ DashboardComponent ]
})

export class AppModule { }
