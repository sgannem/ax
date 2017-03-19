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
var http_1 = require('@angular/http');
var Rx_1 = require('rxjs/Rx');
var HttpAccountService = (function () {
    function HttpAccountService(_http) {
        this._http = _http;
        this._accountServiceData = "https://httpbin.org/get";
    }
    HttpAccountService.prototype.getBasicAccountDetails = function () {
        return this._http.get(this._accountServiceData).map(function (res) { return JSON.stringify(res.json()); }).catch(this.handleError);
        /*return this._http.get('https://httpbin.org/get').map((response:Response) => {
          console.log(response.json());
          response.json();
        });*/
    };
    HttpAccountService.prototype.handleError = function (error) {
        console.error(error);
        return Rx_1.Observable.throw(error.json().error || ' error');
    };
    HttpAccountService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], HttpAccountService);
    return HttpAccountService;
}());
exports.HttpAccountService = HttpAccountService;
//# sourceMappingURL=account.service.js.map