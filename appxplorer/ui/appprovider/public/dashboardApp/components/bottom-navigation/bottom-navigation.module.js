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
var bottom_navigation_component_1 = require('./bottom-navigation.component');
var BottomNavigationModule = (function () {
    function BottomNavigationModule() {
    }
    BottomNavigationModule = __decorate([
        core_1.NgModule({
            imports: [],
            declarations: [bottom_navigation_component_1.BottomNavigationComponent],
            exports: [bottom_navigation_component_1.BottomNavigationComponent]
        }), 
        __metadata('design:paramtypes', [])
    ], BottomNavigationModule);
    return BottomNavigationModule;
}());
exports.BottomNavigationModule = BottomNavigationModule;
//# sourceMappingURL=bottom-navigation.module.js.map