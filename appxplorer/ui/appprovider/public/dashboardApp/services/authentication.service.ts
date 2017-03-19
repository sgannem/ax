import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

export class User {
    constructor(public username:string, public password:string) {

    }
}

@Injectable()
export class HttpAuthenticationService {
  
  private _authenticationData:string = "https://jsonplaceholder.typicode.com/posts/1";
  constructor(private _http: Http, private _router:Router){ }

  checkCredentials() {
    
    if (localStorage.getItem("user") === null) {
        setTimeout(() => {
            this._router.navigate(['/login']);
        });
    }
  }
 
  authenticateService() {
      return this._http.get(this._authenticationData).map(res => JSON.stringify(res.json())).catch(this.handleError);
  }
  
  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json().error || ' error');
  }
}