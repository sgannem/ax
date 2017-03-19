import { Component, Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { CiClassComponent } from '../classComponents/ci-class/ci-class.component';

@Injectable()
export class FeaturedCIService {
  FEATUREDCI:CiClassComponent[] = [
  {id:1, ciName: 'StartBucks', ciType: 'Restaurants',ciDesc:'aaaa', ciLogo:null},
  {id:1, ciName: 'StartBucks', ciType: 'Restaurants',ciDesc:'aaaa', ciLogo:null},
  {id:1, ciName: 'StartBucks', ciType: 'Restaurants',ciDesc:'aaaa', ciLogo:null},
  {id:1, ciName: 'StartBucks', ciType: 'Restaurants',ciDesc:'aaaa', ciLogo:null},
  {id:1, ciName: 'StartBucks', ciType: 'Restaurants',ciDesc:'aaaa', ciLogo:null},
  {id:1, ciName: 'StartBucks', ciType: 'Restaurants',ciDesc:'aaaa', ciLogo:null},
  {id:1, ciName: 'StartBucks', ciType: 'Restaurants',ciDesc:'aaaa', ciLogo:null}
  ];
  private _accountServiceData:string = "https://httpbin.org/get";
  constructor(private _http: Http){ }
 
  getBasicAccountDetails() {
      //return this._http.get(this._accountServiceData).map(res => JSON.stringify(res.json())).catch(this.handleError);
     return this.FEATUREDCI;
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