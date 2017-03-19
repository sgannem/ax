import { Component, OnInit, Input, Inject } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

import { HttpAuthenticationService } from '../../services/authentication.service';
import { LoginRequestDto, LoginRequest } from '../../requests/login.request';
import { LoginResponseDto } from '../../responses/login.response';

import { FlashMessagesService } from 'angular2-flash-messages';
// import { WindowRef } from '../../../utils/windowref';

@Component({
    moduleId: module.id,
    selector: 'login',
    templateUrl: 'login.component.html',
    styleUrls: ['login.component.css'],
    providers: [HttpAuthenticationService]
})

export class LoginComponent implements OnInit {

    public loginForm: FormGroup; // our model driven form
    public submitted: boolean; // keep track on whether form is submitted
    public events: any[] = []; // use later to display form changes
    loginRequestDto: LoginRequestDto;
    loginResponseDto: LoginResponseDto;



    @Input() appname: string = 'default';
    appType: string = 'email';
    successMessage: string;
    messageClass: string;
    // constructor(private winRef: WindowRef, private _fb: FormBuilder, private router: Router, private authenticateService: HttpAuthenticationService, private _flashMessagesService: FlashMessagesService) { }
    //constructor(private _fb: FormBuilder) { }
    constructor( private _fb: FormBuilder, private router: Router, private authenticateService: HttpAuthenticationService, private _flashMessagesService: FlashMessagesService) { }

    ngOnInit() {
        // we will initialize our form model here
        this.loginForm = this._fb.group({
            username: ['', [<any>Validators.required, <any>Validators.minLength(5)]],
            password: ['', [<any>Validators.required, <any>Validators.minLength(5)]]
        });

    }


    login(model: Login, isValid: boolean) {
        console.log("####################this.appname:"+this.appname);
        this.submitted = true; // set form submit to true
        // To authenticate with backend
        this.loginRequestDto = this.constructLoginRequestDTO(model);
        this.authenticateService.login(this.loginRequestDto)
            .subscribe(
            data => {
                console.log(data);
                this.loginResponseDto = JSON.parse(data);
                if (this.loginResponseDto.status == "success") {
                    this.clearFields();
                    if (this.appname == 'ap') {
                        //this.router.navigate(['dashboard-ap']);
                        console.log("Redirecting to http://localhost:3333/dashboardAP");
                        sessionStorage.setItem("apUser", this.loginResponseDto.loginResponse.token);
                        sessionStorage.setItem("apexternaluserid", this.loginResponseDto.loginResponse.externalUserId);
                        console.log("Got the sessionStoreValue for AP:" + sessionStorage.getItem("apUser"));
                        console.log("Got the sessionStoreValue extUserId for AP:" + sessionStorage.getItem("apexternaluserid"));
                        window.open('http://localhost:3333/dashboardAP');
                        // winRef.nativewindow.open('http://localhost:3333/dashboardAP');
                    }
                    else if(this.appname = 'ci') {
                        console.log("Redirecting to http://localhost:3333/dashboardCI");
                        sessionStorage.setItem("ciUser", this.loginResponseDto.loginResponse.token);
                        sessionStorage.setItem("ciexternaluserid", this.loginResponseDto.loginResponse.externalUserId);
                        console.log("Got the sessionStoreValue ciUser for CI:" + sessionStorage.getItem("ciUser"));
                        console.log("Got the sessionStoreValue extUserId for CI:" + sessionStorage.getItem("ciexternaluserid"));
                        window.open('http://localhost:3333/dashboardCI');
                        // winRef.nativewindow.open('http://localhost:3333/dashboardCI');
                    }
                }
                else {
                    this.showErrorMessage(this.loginResponseDto.errorDetails.errorDescription);
                    this.clearFields();
                }
            },
            error => {
            });



    }

    constructLoginRequestDTO(login: Login) {
        return new LoginRequestDto(new LoginRequest(login.username, login.password, this.appType, this.appname));
    }

    showSuccessMessage(message: string) {
        //this._flashMessagesService.show(message, { cssClass: 'alert-success', timeout: 2500 });
        this.successMessage = message;
        this.messageClass = "alert-success";
    }

    showErrorMessage(message: string) {
        //alert(message);
        //this._flashMessagesService.show(message, { cssClass: 'alert-danger', timeout: 2500 });
        this.successMessage = message;
        this.messageClass = "alert-danger";
    }

    clearFields() {
        this.loginForm.get("username").setValue("");
        this.loginForm.get("password").setValue("");
    }
}

export interface Login {
    username: string; // required with minimum 5 chracters
    password: string;
}