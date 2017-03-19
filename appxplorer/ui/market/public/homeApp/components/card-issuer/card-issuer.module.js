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
// Angular Imports
var core_1 = require('@angular/core');
// This Module's Components
var card_issuer_component_1 = require('./card-issuer.component');
var CardissuerModule = (function () {
    function CardissuerModule() {
    }
    CardissuerModule = __decorate([
        core_1.NgModule({
            imports: [],
            declarations: [
                card_issuer_component_1.CardIssuerComponent,
            ],
            exports: [
                card_issuer_component_1.CardIssuerComponent,
            ]
        }), 
        __metadata('design:paramtypes', [])
    ], CardissuerModule);
    return CardissuerModule;
}());
exports.CardissuerModule = CardissuerModule;
//# sourceMappingURL=card-issuer.module.js.map