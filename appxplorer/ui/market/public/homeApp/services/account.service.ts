import { Component, Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class HttpAccountService {
  
  private _accountServiceData:string = "https://httpbin.org/get";
  constructor(private _http: Http){ }
 
  getBasicAccountDetails() {
      return this._http.get(this._accountServiceData).map(res => JSON.stringify(res.json())).catch(this.handleError);
      /*return this._http.get('https://httpbin.org/get').map((response:Response) => {
        console.log(response.json());
        response.json();
      });*/
  }
  
  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json().error || ' error');
  }
}