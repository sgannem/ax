"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var platform_browser_1 = require('@angular/platform-browser');
var http_1 = require('@angular/http');
var router_1 = require('@angular/router');
var forms_1 = require('@angular/forms');
var common_1 = require('@angular/common');
var home_component_1 = require('../components/home/home.component');
var introduction_component_1 = require('../components/introduction/introduction.component');
var card_issuer_component_1 = require('../components/card-issuer/card-issuer.component');
var app_provider_component_1 = require('../components/app-provider/app-provider.component');
var register_component_1 = require('../components/register/register.component');
var login_component_1 = require('../components/login/login.component');
var authentication_service_1 = require('../services/authentication.service');
var authguard_service_1 = require('../services/authguard.service');
var appRoutes = [
    { path: 'introduction', component: introduction_component_1.IntroductionComponent },
    { path: 'cardissuer', component: card_issuer_component_1.CardIssuerComponent },
    { path: 'appprovider', component: app_provider_component_1.AppProviderComponent },
    { path: 'register', component: register_component_1.RegisterComponent },
    { path: 'login', component: login_component_1.LoginComponent },
    // otherwise redirect to Home
    { path: '**', redirectTo: 'introduction', pathMatch: 'full' },
    { path: '', redirectTo: 'introduction', pathMatch: 'full' }
];
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            imports: [platform_browser_1.BrowserModule, http_1.HttpModule, forms_1.ReactiveFormsModule, router_1.RouterModule.forRoot(appRoutes, { useHash: true })],
            //exports: [RouterModule],
            providers: [{ provide: common_1.APP_BASE_HREF, useValue: '/' }, authentication_service_1.HttpAuthenticationService, authguard_service_1.AuthGuard],
            declarations: [home_component_1.HomeComponent, introduction_component_1.IntroductionComponent, card_issuer_component_1.CardIssuerComponent, app_provider_component_1.AppProviderComponent, register_component_1.RegisterComponent, login_component_1.LoginComponent],
            bootstrap: [home_component_1.HomeComponent]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map