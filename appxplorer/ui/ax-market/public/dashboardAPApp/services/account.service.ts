import { Component, Injectable } from '@angular/core';
import { RequestOptions, Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { UpdateAccountDetailsRequestDto } from '../models/account.model';



@Injectable()
export class HttpAccountService {

  private REST_HOST_URL: string = "http://localhost:8080/ax-ciservices/rest/";
  private REST_ACCOUNT_DETAILS_SERVICE = "ci/account/"


  // private _accountServiceData: string = "https://httpbin.org/get";
  constructor(private _http: Http) { }

  // getBasicAccountDetails() {
  //   let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': 'bearer ' + sessionStorage.getItem("ciUser") });
  //   let options = new RequestOptions({ headers: headers });
  //   // return this._http.get(this._accountServiceData).map(res => JSON.stringify(res.json())).catch(this.handleError);
  //   let URL = 'http://localhost:8080/ax-ciservices/rest/ci/account/' + sessionStorage.getItem("ciexternaluserid");
  //   console.info("#calling backend service :" + URL);
  //   return this._http.get(URL)
  //     .map((response: Response) => {
  //       console.log(response.json());
  //       response.json();
  //     });
  // }

  //   login(loginRequestDto: LoginRequestDto){
  //     console.log("Login from Authencation service :" + JSON.stringify(loginRequestDto));
  //     return this._http.post(this.REST_HOST_URL + this.REST_LOGIN_SERVICE, 
  //     JSON.stringify(loginRequestDto)
  //     ,options)
  //             .map(res => JSON.stringify(res.json())).catch(this.handleError);
  //  } 

  readAccountDetails() {
    let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': 'bearer ' + sessionStorage.getItem("apUser") });
    let options = new RequestOptions({ headers: headers });

    let URL = 'http://localhost:8080/ax-ciservices/rest/ci/account/' + sessionStorage.getItem("apexternaluserid");
    console.info("#calling backend service readAccountDetails():" + URL);
    //  return this._http.get(URL, options)
    //  .map((response:Response) => {
    //   console.log(response.json());
    //   response.json();
    // });
    return this._http.get(URL, options)
      .map(res => JSON.stringify(res.json())).catch(this.handleError);
  }

  updateAccountDetails(updateAccountDetailsRequestDto: UpdateAccountDetailsRequestDto) {
    let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': 'bearer ' + sessionStorage.getItem("apUser") });
    let options = new RequestOptions({ headers: headers });
    console.info("#calling backend service updateAccountDetails():" + URL);
    console.info(JSON.stringify(updateAccountDetailsRequestDto));
    return this._http.put('http://localhost:8080/ax-ciservices/rest/ci/account/' + sessionStorage.getItem("apexternaluserid"),
      JSON.stringify(updateAccountDetailsRequestDto), options).map((response: Response) => {
        console.log(response.json());
        response.json();
      });
  }

  getCountries() {
    let headers = new Headers({ 'Authorization': 'bearer ' + sessionStorage.getItem("apUser") });
    let options = new RequestOptions({ headers: headers });
    let URL = 'http://localhost:8080/ax-ciservices/rest/ci/countries';
    console.info("#calling backend service to fetch all the countries URL:" + URL);
    // return this._http.get(URL,
    //   options).map((response: Response) => {
    //     console.log(response.json());
    //     response.json();
    //   });
    return this._http.get(URL, options)
      .map(res => JSON.stringify(res.json()))
      .catch(this.handleError).toPromise();
  }

  getBusinessSegments() {
    let headers = new Headers({ 'Authorization': 'bearer ' + sessionStorage.getItem("apUser") });
    let options = new RequestOptions({ headers: headers });
    let URL = 'http://localhost:8080/ax-ciservices/rest/ci/businesssegments';
    console.info("#calling backend service to fetch all the countries URL:" + URL);
    // return this._http.get(URL,
    //   options).map((response: Response) => {
    //     console.log(response.json());
    //     response.json();
    //   });
    return this._http.get(URL, options)
      .map(res => JSON.stringify(res.json())).catch(this.handleError);
  }

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json().error || ' error');
  }
}