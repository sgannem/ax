import { Component, OnInit, Input, Inject} from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
//import { Router } from '@angular/router';

import { HttpAuthenticationService } from '../../services/authentication.service';
import {SignupRequestDto, LoginRequest} from '../../requests/login.request';
import {SignupResponseDto} from '../../responses/signup.response';


import { FlashMessagesService } from 'angular2-flash-messages';


@Component({
    moduleId: module.id,
    selector: 'register',
    templateUrl: 'register.component.html',
    styleUrls: ['register.component.css'],
    providers: [HttpAuthenticationService]
})

export class RegisterComponent implements OnInit{

  public registerForm: FormGroup; // our model driven form
  public submitted: boolean; // keep track on whether form is submitted
  public events: any[] = []; // use later to display form changes

  @Input() appname: string = 'default';
  appType: string = 'email';

  signupRequestDto: SignupRequestDto;
  signupResponseDto: SignupResponseDto;
  successMessage: string;
messageClass: string;

  //constructor(private _fb: FormBuilder, private router: Router) { }
  constructor(private _fb: FormBuilder, private authenticateService: HttpAuthenticationService, private _flashMessagesService: FlashMessagesService) { }

  ngOnInit() {
        // we will initialize our form model here
        this.registerForm = this._fb.group({
            username: ['', [<any>Validators.required, <any>Validators.minLength(5)]],
            password: ['', [<any>Validators.required, <any>Validators.minLength(5)]]
        });
  }

  register(model: Register, isValid: boolean) {
        this.submitted = true; // set form submit to true
        this.signupRequestDto = this.constructRegisterRequestDTO(model);

        this.authenticateService.register(this.signupRequestDto)
        .subscribe(
                data => {
                    console.log(data);
                    this.signupResponseDto = JSON.parse(data);
                    
                    if(this.signupResponseDto.status=='success') {
                        this.showSuccessMessage("User Created Successfully")
                    }
                    else{
                        this.showErrorMessage(this.signupResponseDto.errorDetails.errorDescription);
                    }
                },
                error => {
                });
        this.clearFields();
    }

    constructRegisterRequestDTO(login : Register)
    {
        return new SignupRequestDto(new LoginRequest(login.username,login.password,this.appType,this.appname ));
    }

    showSuccessMessage(message:string) {
        //this._flashMessagesService.show(message, { cssClass: 'alert-success', timeout: 2500 });
         this.successMessage = message;
        this.messageClass="alert-success";
    }

    showErrorMessage(message:string) {
        //this._flashMessagesService.show(message, { cssClass: 'alert-danger', timeout: 2500 });
        this.successMessage = message;
        this.messageClass="alert-danger";  
    }

    clearFields()
    {
        this.registerForm.get("username").setValue("");
        this.registerForm.get("password").setValue("");
    }

}

export interface Register {
    username: string; // required with minimum 5 chracters
    password: string;
}