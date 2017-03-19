import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import {LoginRequestDto, LoginRequest, SignupRequestDto} from '../requests/login.request';


let headers = new Headers({ 'content-Type': 'application/json'});

let options = new RequestOptions({ headers: headers });

@Injectable()
export class HttpAuthenticationService {
  
  private REST_HOST_URL:string = "http://localhost:8080/ax-ciservices/rest/";
  private REST_LOGIN_SERVICE = "ciaccount/login"
  private REST_REGISTER_SERVICE:string = "ciaccount/signup";

  constructor(private _http: Http, private _router:Router){ }

 login(loginRequestDto: LoginRequestDto){
    console.log("Login from Authencation service :" + JSON.stringify(loginRequestDto));
    return this._http.post(this.REST_HOST_URL + this.REST_LOGIN_SERVICE, 
    JSON.stringify(loginRequestDto)
    ,options)
            .map(res => JSON.stringify(res.json())).catch(this.handleError);
 } 

 register(signupRequestDto: SignupRequestDto){
    console.log("Register/Signup from Authencation service : " + JSON.stringify(signupRequestDto));
    return this._http.post(this.REST_HOST_URL + this.REST_REGISTER_SERVICE, 
    JSON.stringify(signupRequestDto)
    ,options)
            .map(res => JSON.stringify(res.json())).catch(this.handleError);
 } 

  checkCredentials() {
    
    if (localStorage.getItem("user") === null) {
        setTimeout(() => {
            this._router.navigate(['/login']);
        });
    }
  }
 
 /*
  authenticateService() {
      return this._http.get(this._authenticationData).map(res => JSON.stringify(res.json())).catch(this.handleError);
  }
  */

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json().error || ' error');
  }
}