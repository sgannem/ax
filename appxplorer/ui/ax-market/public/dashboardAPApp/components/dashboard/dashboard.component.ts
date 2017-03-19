import { Component, Injectable, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpAccountService } from '../../services/account.service';

@Component({
  moduleId: module.id,
  selector: 'dashboard-ap',
  templateUrl: './dashboard.component.html',
  providers: [HttpAccountService]
})

export class DashboardComponent implements OnInit  {

  public username:string = 'dileepmalayanur';
  public firstName:string = 'Dileep';
  public _accountDetailsUrl:string;
  public _originIPAddress:string;
  
  constructor(private _httpAccountService:HttpAccountService, private router: Router) { }

  private storeAccountDetails(_accountData:string) {
    this._accountDetailsUrl = JSON.parse(_accountData)['url'];
    this._originIPAddress = JSON.parse(_accountData)['origin'];
  }

  private getAccountDetails() {
    //this._httpAccountService.getBasicAccountDetails()
      //.subscribe(_accountDetailsData => this.storeAccountDetails(_accountDetailsData));
  }

  ngOnInit(): void {
   // if(!sessionStorage.getItem("user")){
  // this.router.navigate(['/login']);
  // }
    this.getAccountDetails();
  }
}