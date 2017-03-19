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
var account_service_1 = require('../../services/account.service');
var DashboardComponent = (function () {
    function DashboardComponent(_httpAccountService) {
        this._httpAccountService = _httpAccountService;
        this.username = 'dileepmalayanur';
        this.firstName = 'Dileep';
    }
    DashboardComponent.prototype.storeAccountDetails = function (_accountData) {
        this._accountDetailsUrl = JSON.parse(_accountData)['url'];
        this._originIPAddress = JSON.parse(_accountData)['origin'];
    };
    DashboardComponent.prototype.getAccountDetails = function () {
        var _this = this;
        this._httpAccountService.getBasicAccountDetails()
            .subscribe(function (_accountDetailsData) { return _this.storeAccountDetails(_accountDetailsData); });
    };
    DashboardComponent.prototype.ngOnInit = function () {
        this.getAccountDetails();
    };
    DashboardComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'dashboard',
            templateUrl: './dashboard.component.html',
            providers: [account_service_1.HttpAccountService]
        }), 
        __metadata('design:paramtypes', [account_service_1.HttpAccountService])
    ], DashboardComponent);
    return DashboardComponent;
}());
exports.DashboardComponent = DashboardComponent;
//# sourceMappingURL=dashboard.component.js.map