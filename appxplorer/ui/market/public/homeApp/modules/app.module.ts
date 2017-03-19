import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { APP_BASE_HREF } from '@angular/common';

import { HomeComponent }  from '../components/home/home.component';
import { IntroductionComponent }  from '../components/introduction/introduction.component';
import { CardIssuerComponent }  from '../components/card-issuer/card-issuer.component';
import { AppProviderComponent }  from '../components/app-provider/app-provider.component';
import { RegisterComponent }  from '../components/register/register.component';
import { LoginComponent }  from '../components/login/login.component';

import { HttpAuthenticationService }  from '../services/authentication.service';
import { AuthGuard }  from '../services/authguard.service';

const appRoutes: Routes = [
    { path: 'introduction', component: IntroductionComponent },
    { path: 'cardissuer', component: CardIssuerComponent },
    { path: 'appprovider', component: AppProviderComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'login', component: LoginComponent },

    // otherwise redirect to Home
    { path: '**', redirectTo: 'introduction', pathMatch: 'full' },
    { path: '', redirectTo: 'introduction', pathMatch: 'full' }
];

@NgModule({
  imports: [ BrowserModule, HttpModule, ReactiveFormsModule, RouterModule.forRoot(appRoutes, {useHash: true}) ],
  //exports: [RouterModule],
  providers: [{provide: APP_BASE_HREF, useValue : '/' }, HttpAuthenticationService, AuthGuard],
  declarations: [ HomeComponent, IntroductionComponent, CardIssuerComponent, AppProviderComponent, RegisterComponent, LoginComponent ],
  bootstrap:    [ HomeComponent ]
})

export class AppModule { }
