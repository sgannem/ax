import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import {LookupResponseDto} from '../responses/lookup.response';
import {HTTPHeader} from '../commons/httpheader';
 

@Injectable()
export class MyCardService {

  private REST_HOST_URL:string = "http://localhost:8080/ax-ciservices/rest/";
  private REST_SMART_MEDIUM_TYPE_LIST_SERVICE = "ciaccount/login"
  private lookUpList: LookupResponseDto;

  constructor(private _http: Http, private httpHeader: HTTPHeader){ }


 getSmartMediumTypeList(){
    /* 
    this.doGet(this.REST_HOST_URL + this.REST_SMART_MEDIUM_TYPE_LIST_SERVICE)
    .subscribe(
                data => {
                    console.log(data);
                    this.lookUpList =JSON.parse(data);
                },
                error => {
                });
                */

      return JSON.parse(this.mockSmartMediumTypeListResponse());          
 } 

  getTechnologyList(){

      return JSON.parse(this.mockTechnologyListResponse());          
 } 

  getSmartMediumSizeList(){

      return JSON.parse(this.mockSmartMediumSizeListResponse());          
 } 

getCountryList()
{
    return JSON.parse(this.mockCountryListResponse());
}

getBussinessSegmentList()
{
    return JSON.parse(this.mockBussinessSegmentList());
}

private doGet(url: string)
{
    return this._http.get(url
    ,this.httpHeader.getHeaderOptions())
            .map(res => JSON.stringify(res.json())).catch(this.handleError);

}

private mockSmartMediumTypeListResponse()
{
    return '{"status":"success","lookupResponse":[{"id":"0","name":"Smartcard"},{"id":"1","name":"Wearable"}]}';
}

private mockTechnologyListResponse()
{
    return '{"status":"success","lookupResponse":[{"id":"0","name":"MIFARE DESFire EV2"},{"id":"1","name":"MIFARE DESFire EV1"}]}';
}

private mockSmartMediumSizeListResponse()
{
    return '{"status":"success","lookupResponse":[{"id":"0","name":"8 KB"},{"id":"1","name":"4 KB"}]}';
}

private mockCountryListResponse()
{
    return '{"status":"success","countryListResponse":[{"id":"0","text":"India"},{"id":"1","text":"USA"},{"id":"2","text":"Canada"},{"id":"3","text":"UK"}]}';
    
}

private mockBussinessSegmentList()
{
    return '{"status":"success","bussinessSegmentResponse":[{"id":"0","text":"Choose a business segment"},{"id":"1","text":"Airlines"},{"id":"2","text":"Amusement and entertainment"},{"id":"3","text":"Associations"}]}';
}

 private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json().error || ' error');
  }
}