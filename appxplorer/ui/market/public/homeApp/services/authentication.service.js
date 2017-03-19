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
var router_1 = require('@angular/router');
var http_1 = require('@angular/http');
var Rx_1 = require('rxjs/Rx');
var User = (function () {
    function User(username, password) {
        this.username = username;
        this.password = password;
    }
    return User;
}());
exports.User = User;
var HttpAuthenticationService = (function () {
    function HttpAuthenticationService(_http, _router) {
        this._http = _http;
        this._router = _router;
        this._authenticationData = "https://jsonplaceholder.typicode.com/posts/1";
    }
    HttpAuthenticationService.prototype.checkCredentials = function () {
        var _this = this;
        if (localStorage.getItem("user") === null) {
            setTimeout(function () {
                _this._router.navigate(['/login']);
            });
        }
    };
    HttpAuthenticationService.prototype.authenticateService = function () {
        return this._http.get(this._authenticationData).map(function (res) { return JSON.stringify(res.json()); }).catch(this.handleError);
    };
    HttpAuthenticationService.prototype.handleError = function (error) {
        console.error(error);
        return Rx_1.Observable.throw(error.json().error || ' error');
    };
    HttpAuthenticationService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http, router_1.Router])
    ], HttpAuthenticationService);
    return HttpAuthenticationService;
}());
exports.HttpAuthenticationService = HttpAuthenticationService;
//# sourceMappingURL=authentication.service.js.map