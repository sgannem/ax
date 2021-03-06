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
var register_component_1 = require('./register.component');
var TopNavigationModule = (function () {
    function TopNavigationModule() {
    }
    TopNavigationModule = __decorate([
        core_1.NgModule({
            imports: [],
            declarations: [
                register_component_1.RegisterComponent,
            ],
            exports: [
                register_component_1.RegisterComponent,
            ]
        }), 
        __metadata('design:paramtypes', [])
    ], TopNavigationModule);
    return TopNavigationModule;
}());
exports.TopNavigationModule = TopNavigationModule;
//# sourceMappingURL=register.module.js.map