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
var forms_1 = require('@angular/forms');
var router_1 = require('@angular/router');
var common_1 = require('@angular/common');
var dashboard_component_1 = require('../components/dashboard/dashboard.component');
var sidebar_navigation_component_1 = require('../components/sidebar-navigation/sidebar-navigation.component');
var top_navigation_component_1 = require('../components/top-navigation/top-navigation.component');
var bottom_navigation_component_1 = require('../components/bottom-navigation/bottom-navigation.component');
var dashboard_home_component_1 = require('../components/dashboard-home/dashboard-home.component');
var myapps_component_1 = require('../components/myapps/myapps.component');
var requests_component_1 = require('../components/requests/requests.component');
var account_component_1 = require('../components/account/account.component');
var authentication_service_1 = require('../services/authentication.service');
var authguard_service_1 = require('../services/authguard.service');
var appRoutes = [
    { path: 'show-home', component: dashboard_home_component_1.DashboardHomeComponent },
    { path: 'show-myapps', component: myapps_component_1.MyAppsComponent },
    { path: 'show-requests', component: requests_component_1.RequestsComponent },
    { path: 'show-account', component: account_component_1.AccountComponent },
    // otherwise redirect to Home
    { path: '**', redirectTo: '/show-home' },
    { path: '', redirectTo: '/show-home', pathMatch: 'full' },
];
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            imports: [platform_browser_1.BrowserModule, http_1.HttpModule, forms_1.ReactiveFormsModule, router_1.RouterModule.forRoot(appRoutes, { useHash: true })],
            //exports: [RouterModule],
            providers: [{ provide: common_1.APP_BASE_HREF, useValue: '/' }, authentication_service_1.HttpAuthenticationService, authguard_service_1.AuthGuard],
            declarations: [dashboard_component_1.DashboardComponent, sidebar_navigation_component_1.SidebarNavigationComponent, top_navigation_component_1.TopNavigationComponent, bottom_navigation_component_1.BottomNavigationComponent, dashboard_home_component_1.DashboardHomeComponent, myapps_component_1.MyAppsComponent, requests_component_1.RequestsComponent, account_component_1.AccountComponent],
            bootstrap: [dashboard_component_1.DashboardComponent]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map