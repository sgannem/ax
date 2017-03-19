import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { RequestDetail } from './RequestDetail';

@Component({
  moduleId: module.id,
  selector: 'pendingrequests',
  templateUrl: 'pendingrequests.component.html',
  styleUrls: ['pendingrequests.component.css']
})
export class PendingrequestsComponent {
  public requestStatus: string;
  public headerClass: string;
  public logoClass: string;
  public tableClass: string;
  public sub: any;
  public requestDetails: RequestDetail[] = [];
  public selectedRequestDetail: RequestDetail;

  constructor(
    private route: ActivatedRoute,
    private router: Router) {

  }

  ngOnInit() {

    this.sub = this.route
      .queryParams
      .subscribe(params => {
        // Defaults to 0 if no query param provided.
        this.requestStatus = params['requestStatus'];
        console.log("page" + this.requestStatus);
        this.loadRequestsList();
        this.styleSet();
      });


  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  accepted(selectedRequestDetail: RequestDetail) {
    //TODO update the status of selected request to accepted in database
    this.requestDetails.splice(this.requestDetails.indexOf(selectedRequestDetail), 1);
    this.selectedRequestDetail = this.requestDetails[0];
    //this.ngOnInit();
  }

  declined(selectedRequestDetail: RequestDetail) {
    //TODO update the status of selected request to declined in database
    this.requestDetails.splice(this.requestDetails.indexOf(selectedRequestDetail), 1);
    this.selectedRequestDetail = this.requestDetails[0];
    this.ngOnInit();
  }

  styleSet() {
    if (this.requestStatus == 'Pending') {
      this.headerClass = 'row bold text-warning-custom';
      this.logoClass = 'fa fa-hourglass fa-stack-1x font-size';
      this.tableClass = 'table padding-table pending-table-color';
    }
    else if (this.requestStatus == 'Accepted') {
      this.headerClass = 'row bold text-success-custom';
      this.logoClass = 'fa fa-check fa-stack-1x font-size';
      this.tableClass = 'table padding-table accepted-table-color';
    }
    else {
      this.headerClass = 'row bold text-danger-custom';
      this.logoClass = 'fa fa-times fa-stack-1x font-size';
      this.tableClass = 'table padding-table declined-table-color';
    }
  }

  loadRequestsList() {
    this.requestDetails.push(new RequestDetail('Card Issuer name', '01/2017', 'Application Name', '800KB', '$0.4 per installment', 'Industry:Clothing', 'Dedicated', 'App Name1', 'NXP, Card Issuer, Temrs nd conditions', '100', '320 Bytes', 'pending','Graz+30km', 'Terms and condition cannot be acepted'));
    this.requestDetails.push(new RequestDetail('Card Issuer name1', '01/2017', 'Application Name1', '800KB', '$0.4 per installment', 'Industry:Clothing', 'Dedicated', 'App Name2', 'NXP,Card Issuer, Temrs nd conditions', '100', '320 Bytes', 'pending','Graz+30km', 'Terms and condition cannot be acepted'));
    this.requestDetails.push(new RequestDetail('Card Issuer name2', '01/2017', 'Application Name2', '800KB', '$0.4 per installment', 'Industry:Clothing', 'Dedicated', 'App Name3', 'NXP, Card Issuer, Temrs nd conditions', '100', '320 Bytes', 'pending','Graz+30km', 'Terms and condition cannot be acepted'));
    this.requestDetails.push(new RequestDetail('Card Issuer name3', '01/2017', 'Application Name3', '800KB', '$0.4 per installment', 'Industry:Clothing', 'Dedicated', 'App Name4', 'NXP,Card Issuer, Temrs nd conditions', '100', '320 Bytes', 'pending','Graz+30km', 'Terms and condition cannot be acepted'));
    this.requestDetails.push(new RequestDetail('Card Issuer name4', '01/2017', 'Application Name4', '800KB', '$0.4 per installment', 'Industry:Clothing', 'Dedicated', 'App Name5', 'NXP, Card Issuer, Temrs nd conditions', '100', '320 Bytes', 'approved','Graz+30km', 'Terms and condition cannot be acepted'));
    this.requestDetails.push(new RequestDetail('Card Issuer name5', '01/2017', 'Application Name5', '800KB', '$0.4 per installment', 'Industry:Clothing', 'Dedicated', 'App Name6', 'NXP, Card Issuer, Temrs nd conditions', '100', '320 Bytes', 'approved','Graz+30km', 'Terms and condition cannot be acepted'));
    this.requestDetails.push(new RequestDetail('Card Issuer name6', '01/2017', 'Application Name6', '800KB', '$0.4 per installment', 'Industry:Clothing', 'Dedicated', 'App Name7', 'NXP, Card Issuer, Temrs nd conditions', '100', '320 Bytes', 'approved','Graz+30km', 'Terms and condition cannot be acepted'));
    this.requestDetails.push(new RequestDetail('Card Issuer name7', '01/2017', 'Application Name7', '800KB', '$0.4 per installment', 'Industry:Clothing', 'Dedicated', 'App Name8', 'NXP, Card Issuer, Temrs nd conditions', '100', '320 Bytes', 'declined','Graz+30km', 'Terms and condition cannot be acepted'));
    this.requestDetails.push(new RequestDetail('Card Issuer name8', '01/2017', 'Application Name8', '800KB', '$0.4 per installment', 'Industry:Clothing', 'Dedicated', 'App Name9', 'NXP, Card Issuer, Temrs nd conditions', '100', '320 Bytes', 'declined','Graz+30km', 'Terms and condition cannot be acepted'));
    this.requestDetails.push(new RequestDetail('Card Issuer name8', '01/2017', 'Application Name8', '800KB', '$0.4 per installment', 'Industry:Clothing', 'Dedicated', 'App Name9', 'NXP, Card Issuer, Temrs nd conditions', '100', '320 Bytes', 'declined','Graz+30km', 'Terms and condition cannot be acepted'));
    this.requestDetails.push(new RequestDetail('Card Issuer name8', '01/2017', 'Application Name8', '800KB', '$0.4 per installment', 'Industry:Clothing', 'Dedicated', 'App Name9', 'NXP, Card Issuer, Temrs nd conditions', '100', '320 Bytes', 'declined','Graz+30km', 'Terms and condition cannot be acepted'));
    this.requestDetails.push(new RequestDetail('Card Issuer name8', '01/2017', 'Application Name8', '800KB', '$0.4 per installment', 'Industry:Clothing', 'Dedicated', 'App Name9', 'NXP, Card Issuer, Temrs nd conditions', '100', '320 Bytes', 'declined','Graz+30km', 'Terms and condition cannot be acepted'));
    this.requestDetails.push(new RequestDetail('Card Issuer name8', '01/2017', 'Application Name8', '800KB', '$0.4 per installment', 'Industry:Clothing', 'Dedicated', 'App Name9', 'NXP, Card Issuer, Temrs nd conditions', '100', '320 Bytes', 'declined','Graz+30km', 'Terms and condition cannot be acepted'));

    this.selectedRequestDetail = this.requestDetails[0];

  }

}
