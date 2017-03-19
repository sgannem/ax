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
var ci_class_component_1 = require('./ci-class.component');
var CiClassModule = (function () {
    function CiClassModule() {
    }
    CiClassModule = __decorate([
        core_1.NgModule({
            imports: [],
            declarations: [
                ci_class_component_1.CiClassComponent,
            ],
            exports: [
                ci_class_component_1.CiClassComponent,
            ]
        }), 
        __metadata('design:paramtypes', [])
    ], CiClassModule);
    return CiClassModule;
}());
exports.CiClassModule = CiClassModule;
//# sourceMappingURL=ci-class.module.js.map