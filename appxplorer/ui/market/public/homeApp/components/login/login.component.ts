import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

import { HttpAuthenticationService } from '../../services/authentication.service';

@Component({
    moduleId: module.id,
    selector: 'login',
    templateUrl: 'login.component.html',
    styleUrls: ['login.component.scss'],
    providers: [HttpAuthenticationService]
})

export class LoginComponent implements OnInit {

  public loginForm: FormGroup; // our model driven form
  public submitted: boolean; // keep track on whether form is submitted
  public events: any[] = []; // use later to display form changes
  @Input() appname: string = 'default';

  constructor(private _fb: FormBuilder, private router: Router) { }
  //constructor(private _fb: FormBuilder) { }

  ngOnInit() {
        // we will initialize our form model here
        this.loginForm = this._fb.group({
            username: ['', [<any>Validators.required, <any>Validators.minLength(5)]],
            password: ['', [<any>Validators.required, <any>Validators.minLength(5)]]
        });
  }

  login(model: Login, isValid: boolean) {
        this.submitted = true; // set form submit to true
        console.log(this.appname);
        if(model.username=='dileep') {
            console.log('Sign In validated !!!');
            if(this.appname!=undefined && this.appname=='ci') {
                document.location.href = 'http://localhost:4444/';
            } else if(this.appname!=undefined && this.appname=='ap') {
                document.location.href = 'http://localhost:5555/';
            }
            console.log(model, isValid);
        }
    }
}

export interface Login {
    username: string; // required with minimum 5 chracters
    password: string;
}