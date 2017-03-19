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
var forms_1 = require('@angular/forms');
var router_1 = require('@angular/router');
var authentication_service_1 = require('../../services/authentication.service');
var LoginComponent = (function () {
    function LoginComponent(_fb, router) {
        this._fb = _fb;
        this.router = router;
        this.events = []; // use later to display form changes
        this.appname = 'default';
    }
    //constructor(private _fb: FormBuilder) { }
    LoginComponent.prototype.ngOnInit = function () {
        // we will initialize our form model here
        this.loginForm = this._fb.group({
            username: ['', [forms_1.Validators.required, forms_1.Validators.minLength(5)]],
            password: ['', [forms_1.Validators.required, forms_1.Validators.minLength(5)]]
        });
    };
    LoginComponent.prototype.login = function (model, isValid) {
        this.submitted = true; // set form submit to true
        console.log(this.appname);
        if (model.username == 'dileep') {
            console.log('Sign In validated !!!');
            if (this.appname != undefined && this.appname == 'ci') {
                document.location.href = 'http://localhost:4444/';
            }
            else if (this.appname != undefined && this.appname == 'ap') {
                document.location.href = 'http://localhost:5555/';
            }
            console.log(model, isValid);
        }
    };
    __decorate([
        // use later to display form changes
        core_1.Input(), 
        __metadata('design:type', String)
    ], LoginComponent.prototype, "appname", void 0);
    LoginComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'login',
            templateUrl: 'login.component.html',
            styleUrls: ['login.component.scss'],
            providers: [authentication_service_1.HttpAuthenticationService]
        }), 
        __metadata('design:paramtypes', [forms_1.FormBuilder, router_1.Router])
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map