import { Component } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
//import { Router } from '@angular/router';

import { HttpAuthenticationService } from '../../services/authentication.service';

@Component({
    moduleId: module.id,
    selector: 'register',
    templateUrl: 'register.component.html'
})

export class RegisterComponent {

  public registerForm: FormGroup; // our model driven form
  public submitted: boolean; // keep track on whether form is submitted
  public events: any[] = []; // use later to display form changes

  //constructor(private _fb: FormBuilder, private router: Router) { }
  constructor(private _fb: FormBuilder) { }

  ngOnInit() {
        // we will initialize our form model here
        this.registerForm = this._fb.group({
            username: ['', [<any>Validators.required, <any>Validators.minLength(5)]],
            password: ['', [<any>Validators.required, <any>Validators.minLength(5)]]
        });
  }

  register(model: Register, isValid: boolean) {
        this.submitted = true; // set form submit to true
        if(model.username=='dileep') {
          localStorage.setItem('username', model.username);
          //this.router.navigate(['/dashboard']);
        }
        // check if model is valid
        // if valid, call API to save customer
        console.log(model, isValid);
    }
}


export interface Register {
    username: string; // required with minimum 5 chracters
    password: string;
}