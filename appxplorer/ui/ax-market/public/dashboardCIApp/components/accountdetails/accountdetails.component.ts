import { Component, OnInit, AfterViewInit } from '@angular/core';
import { HttpAccountService } from '../../services/account.service';
import { FormBuilder, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { FormControl, FormGroup } from '@angular/forms';
import { AccountDetails } from './accountdetails';
import { FlashMessagesService } from 'angular2-flash-messages';
import { ViewAccountDetailsResponseDto, UpdateAccountDetailsRequestDto, UpdateAccountDetailsRequest, UpdateAccountDetailsResponseDto, UpdateAccountDetailsResponse } from '../../models/account.model';
import { Country, CountryResponse, CountryResponseDto } from '../../models/account.model';
import { BusinessSegmentResponseDto } from '../../models/account.model';
import { Select2OptionData } from 'ng2-select2/ng2-select2';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
    moduleId: module.id,
    selector: 'accountdetails',
    templateUrl: 'accountdetails.component.html',
    styleUrls: ['accountdetails.component.css'],
    providers: [HttpAccountService]
})


export class AccountDetailsComponent implements OnInit {
    public myForm: FormGroup;
    public submitted: boolean;
    public events: any[] = [];
    public viewAccountDetailsResponseDto: ViewAccountDetailsResponseDto;
    public updateAccountDetailsResponseDto: UpdateAccountDetailsResponseDto;
    public updateAccountDetailsRequestDto: UpdateAccountDetailsRequestDto;
    public countryResponseDto: CountryResponseDto;
    public businessSegmentResponseDto: BusinessSegmentResponseDto;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    companyName: string;
    companyAddress: string;
    zipCode: string;
    country: string;
    businessSegment: string;
    logoFilesToUpload: Array<File>;
    tncFilesToUpload: Array<File>;
    logoBytes: any;
    tncBytes: any;
    imageURL: any;
    tncURL: any;
    imageDataString: string;
    tncPdfDataString: string;
    imageDataString1: any;
    tncPdfDataString1: any;
    isImageExists: boolean = false;
    isTncExists: boolean = false;

    $: JQueryStatic;
    public businessSegments: Array<Select2OptionData> = [];
    public countries: Array<Select2OptionData> = [];


    constructor(private _santizer: DomSanitizer, private _fb: FormBuilder, private avd: HttpAccountService,
        private _flashMessagesService: FlashMessagesService) {
        this.logoFilesToUpload = [];
        this.tncFilesToUpload = [];

    }


    ngOnInit() {

        // this.imageURL = "abc";
        // var x = this.string2Bin(this.imageURL);
        // console.log("string to binary:"+x);
        // console.log("binary to string:"+this.bin2string(x));


        // this.avd.getCountries()
        //     .subscribe(
        //     data => {
        //         console.log("#got the countries json data:" + data);
        //         this.countryResponseDto = JSON.parse(data);
        //         if (this.countryResponseDto.status === 'success') {
        //             console.log("#all the countries are fetched successfully");
        //             for (var i = 0; i < this.countryResponseDto.countryResponse.countries.length; i++) {
        //                 console.log({
        //                     id: '' + this.countryResponseDto.countryResponse.countries[i].threeCharIsoCountryCode,
        //                     text: '' + this.countryResponseDto.countryResponse.countries[i].countryName
        //                 });
        //                 this.countries.push({
        //                     id: '' + this.countryResponseDto.countryResponse.countries[i].threeCharIsoCountryCode,
        //                     text: '' + this.countryResponseDto.countryResponse.countries[i].countryName
        //                 });
        //             }
        //             console.log(this.countries.length);

        //         }
        //     });

        this.avd.getCountries().then(
            data => {
                // console.log("#got the countries json data:" + data);
                this.countryResponseDto = JSON.parse(data);
                if (this.countryResponseDto.status === 'success') {
                    console.log("#all the countries are fetched successfully");
                    for (var i = 0; i < this.countryResponseDto.countryResponse.countries.length; i++) {
                        // console.log({
                        //     id: '' + this.countryResponseDto.countryResponse.countries[i].threeCharIsoCountryCode,
                        //     text: '' + this.countryResponseDto.countryResponse.countries[i].countryName
                        // });
                        this.countries.push({
                            id: '' + this.countryResponseDto.countryResponse.countries[i].threeCharIsoCountryCode,
                            text: '' + this.countryResponseDto.countryResponse.countries[i].countryName
                        });
                    }
                    console.log(this.countries.length);

                }
            }
        );

        this.avd.getBusinessSegments()
            .subscribe(
            data => {
                console.log("#got the businesssegments json data:" + data);
                this.businessSegmentResponseDto = JSON.parse(data);
                if (this.businessSegmentResponseDto.status === 'success') {
                    console.log("#all the business segments are fetched successfully");
                    for (var i = 0; i < this.businessSegmentResponseDto.businessSegmentResponse.businessSegments.length; i++) {
                        console.log({
                            id: '' + this.businessSegmentResponseDto.businessSegmentResponse.businessSegments[i].id,
                            text: '' + this.businessSegmentResponseDto.businessSegmentResponse.businessSegments[i].name
                        });
                        this.businessSegments.push({
                            id: '' + this.businessSegmentResponseDto.businessSegmentResponse.businessSegments[i].id,
                            text: '' + this.businessSegmentResponseDto.businessSegmentResponse.businessSegments[i].name
                        });
                    }
                    console.log(this.businessSegments.length);

                }
            });

        //      this.countries = [
        //     { id: '', text: 'select the country of your company address' },
        //     { id: 'AU', text: 'Austria' },
        //     { id: 'GE', text: 'Germany' },
        //     { id: 'IN', text: 'India' }

        // ];

        this.country = 'IND';

        //   this.businessSegments = [
        //     { id: '', text: 'Choose a business segment' },
        //     { id: '1', text: 'Airlines' },
        //     { id: '2', text: 'Amusement and entertainment' },
        //     { id: '3', text: 'Associations' }
        // ];

        this.businessSegment = '1';



        this.myForm = this._fb.group({
            firstName: [""],
            lastName: [""],
            email: [""],
            password: [""],
            companyName: [""],
            companyAddress: [""],
            zipCode: [""],
            //country: [""],
            // businessSegment: [""],
            // logo: [],
            // tnc: []

        });



        this.avd.readAccountDetails()
            .subscribe(
            data => {
                console.log(data);
                this.viewAccountDetailsResponseDto = JSON.parse(data);
                if (this.viewAccountDetailsResponseDto.status === 'success') {
                    // this.showSuccessMessage("Service invoked successfully..." + this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.firstName);
                    this.firstName = this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.firstName;
                    this.lastName = this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.lastName;
                    this.email = this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.email;
                    this.password = this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.password;
                    this.companyName = this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.companyName;
                    this.companyAddress = this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.companyName;
                    this.zipCode = this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.zipCode;
                    // this.country = this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.country;
                    // this.businessSegment = this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.businessSegment;
                    if (this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.companyOrBusinessLogo) {
                        this.isImageExists = true;
                        this.imageURL = 'data:image/png;base64,' + this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.companyOrBusinessLogo;
                        console.log("image ===========>:" + this.imageURL);
                    } else {
                        console.log("=============> companyOrBusinessLogo does not set");
                    }
                    if (this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.uploadYourTermsAndConditions) {
                        this.isTncExists = true;
                        this.tncURL = 'data:application/pdf;base64,' + this.viewAccountDetailsResponseDto.viewAccountDetailsResponse.uploadYourTermsAndConditions;
                        console.log("tncURL==============>:" + this.tncURL);
                    } else {
                        console.log("=============> TNC does not set")
                    }
                    (<FormControl>this.myForm.controls['firstName'])
                        .setValue(this.firstName, { onlySelf: true });

                    (<FormControl>this.myForm.controls['lastName'])
                        .setValue(this.lastName, { onlySelf: true });

                    (<FormControl>this.myForm.controls['email'])
                        .setValue(this.email, { onlySelf: true });

                    (<FormControl>this.myForm.controls['password'])
                        .setValue(this.password, { onlySelf: true });

                    (<FormControl>this.myForm.controls['companyName'])
                        .setValue(this.companyName, { onlySelf: true });

                    (<FormControl>this.myForm.controls['companyAddress'])
                        .setValue(this.companyAddress, { onlySelf: true });

                    (<FormControl>this.myForm.controls['zipCode'])
                        .setValue(this.zipCode, { onlySelf: true });

                    //  (<FormControl>this.myForm.controls['country'])
                    //     .setValue(this.country, { onlySelf: true });

                    // (<FormControl>this.myForm.controls['businessSegment'])
                    //     .setValue(this.businessSegment, { onlySelf: true });

                    // (<FormControl>this.myForm.controls['logo'])
                    //     .setValue(this.logoBytes, { onlySelf: true });

                    // (<FormControl>this.myForm.controls['tnc'])
                    //     .setValue(this.tncBytes, { onlySelf: true });

                } else if (this.viewAccountDetailsResponseDto.status === 'failed') {
                    // this.showErrorMessage("Service invoked successfully..." + this.viewAccountDetailsResponseDto.errorDetails.errorCode);
                }
            },
            error => {
                // TODO error handling
            });

        // this.showErrorMessage('please fill all the account details for getting reviewed by nxp');

        console.log("#firstName:" + this.firstName + ", lastName:" + this.lastName + ", email:" +
            this.email + ", password:" + this.password + ", companyName:" + this.companyName + ", companyAddress:" +
            this.companyAddress + ", zipCode:" + this.zipCode + ", country:" + this.country + ", businessSegment:" + this.businessSegment
            + ", companyOrBusinessLogo:" + this.logoBytes + ", tnc:" + this.tncBytes);

        // Update single value
        // (<FormControl>this.myForm.controls['firstName'])
        //     .setValue(this.firstName, { onlySelf: true });

        // (<FormControl>this.myForm.controls['lastName'])
        //     .setValue(this.lastName, { onlySelf: true });

        // (<FormControl>this.myForm.controls['email'])
        //     .setValue(this.email, { onlySelf: true });

        // (<FormControl>this.myForm.controls['password'])
        //     .setValue(this.password, { onlySelf: true });

        // (<FormControl>this.myForm.controls['companyName'])
        //     .setValue(this.companyName, { onlySelf: true });

        // subscribe to form changes  
        // this.subcribeToFormChanges();



        // Update form model
        // const people = {
        // 	name: 'Jane',
        // 	address: {
        // 		street: 'High street',
        // 		postcode: '94043'
        // 	}
        // };

        // (<FormGroup>this.myForm)
        //     .setValue(people, { onlySelf: true });





        // setTimeout("$('#termsAndConditions').fileinput({'browseClass': 'fileUploadBtn', 'browseLabel': 'Choose file ...', 'showCaption': false, 'showPreview': true, 'showUpload': false, 'showClose': false, 'showRemove': false, 'showCancel': false, 'previewFileType':'image'})", 100);
        // setTimeout("$('#cardLogo').fileinput({'browseClass': 'fileUploadBtn', 'browseLabel': 'Choose file ...', 'showCaption': false, 'showPreview': true, 'showUpload': false, 'showClose': false, 'showRemove': false, 'showCancel': false, 'previewFileType':'image'})", 100);
        // setTimeout("$('.kv-upload-progress').hide()", 200);

    }



    subcribeToFormChanges() {
        // const myFormStatusChanges$ = this.myForm.statusChanges;
        // const myFormValueChanges$ = this.myForm.valueChanges;

        // myFormStatusChanges$.subscribe(x => this.events.push({ event: 'STATUS_CHANGED', object: x }));
        // myFormValueChanges$.subscribe(x => this.events.push({ event: 'VALUE_CHANGED', object: x }));
    }

    isDisabled(link) {
        // alert("Disabled");
        return link === 'Other';
    }

    save(model: AccountDetails, isValid: boolean) {
        this.submitted = true;
        console.log("####################:" + model, "form is valid:" + isValid);
        console.log("#firstName:" + model.firstName + ', lastName:' + model.lastName + ", email:" + model.email +
            ", password:" + model.password + ", companyName:" + this.companyName);
        this.updateAccountDetailsRequestDto = this.constructUpdateAccountDetailsRequestDto(model, this.imageDataString1, this.tncPdfDataString1);
        console.log("#updateAccountDetailsRequestDto:" + this.updateAccountDetailsRequestDto);


        this.avd.updateAccountDetails(this.updateAccountDetailsRequestDto).subscribe(
            data => {
                console.log(data);
                this.showSuccessMessage("Account Details are updated successfully");
                this.updateAccountDetailsResponseDto = JSON.parse(data);
                if (this.updateAccountDetailsResponseDto.status === 'success') {
                    this.showSuccessMessage("Account details are saved successfully");
                    (<FormControl>this.myForm.controls['firstName'])
                        .setValue(this.firstName, { onlySelf: true });

                    (<FormControl>this.myForm.controls['lastName'])
                        .setValue(this.lastName, { onlySelf: true });

                    (<FormControl>this.myForm.controls['email'])
                        .setValue(this.email, { onlySelf: true });

                    (<FormControl>this.myForm.controls['password'])
                        .setValue(this.password, { onlySelf: true });

                    (<FormControl>this.myForm.controls['companyName'])
                        .setValue(this.companyName, { onlySelf: true });

                    (<FormControl>this.myForm.controls['companyAddress'])
                        .setValue(this.companyAddress, { onlySelf: true });
                }
            },
            error => {
                // TODO error handling
            });

    }

    showSuccessMessage(message: string) {
        this._flashMessagesService.show(message, { cssClass: 'alert-success', timeout: 2500 });
    }

    showErrorMessage(message: string) {
        this._flashMessagesService.show(message, { cssClass: 'alert-danger', timeout: 2500 });
    }

    constructUpdateAccountDetailsRequestDto(accountDetails: AccountDetails, imageData: any, pdfData: any) {
        return new UpdateAccountDetailsRequestDto(new UpdateAccountDetailsRequest(accountDetails.firstName, accountDetails.lastName, accountDetails.email, accountDetails.password,
            accountDetails.companyName, accountDetails.companyAddress, accountDetails.zipCode,
            accountDetails.country, accountDetails.businessSegment, accountDetails.logo, accountDetails.tnc), imageData, pdfData);
    }



    uploadLogoFileChangeEvent(inputValue: any) {
        let test123: any;
        console.log('uploadFile::' + inputValue.target.files);
        this.logoFilesToUpload = <Array<File>>inputValue.target.files;

        for (var i = 0; i < this.logoFilesToUpload.length; i++) {
            console.log("uploads[]", this.logoFilesToUpload[i], this.logoFilesToUpload[i].name);
            let file: File = this.logoFilesToUpload[i];
            console.log("#got file:" + file);
            let myReader: FileReader = new FileReader();
            console.log("#Got file reader:" + myReader);


            if (!file) return Promise.reject(null);
            let reader = new FileReader();
            let processPromise = new Promise((resolve, reject) => {
                reader.onload = (e: ProgressEvent) => {
                    resolve((<any>e.target).result);
                }
            });
            reader.readAsBinaryString(file);
            processPromise.then((data) => {
                this.logoBytes = data;
                this.imageDataString1 = data;
                // var url = 'data:image/png;base64,' + data;
                var url = '' + data;
                // this.imageURL = this._santizer.bypassSecurityTrustHtml(url);
                console.log("#image data:" + data);
                this.imageDataString = url;
                this.imageDataString = btoa(this.imageDataString);
                console.log("#image data in base64 format:" + this.imageDataString);
                this.isImageExists = true;
                this.imageURL = 'data:image/png;base64,' + this.imageDataString;
            }).catch((ex) => {
                console.error('Error fetching image', ex);
            });
        }

    }

    uploadTncFileChangeEvent(inputValue: any) {
        let test123: any;
        console.log('uploadFile::' + inputValue.target.files);
        this.tncFilesToUpload = <Array<File>>inputValue.target.files;

        for (var i = 0; i < this.tncFilesToUpload.length; i++) {
            console.log("uploads[]", this.tncFilesToUpload[i], this.tncFilesToUpload[i].name);
            let file: File = this.tncFilesToUpload[i];
            console.log("#got file:" + file);
            let myReader: FileReader = new FileReader();
            console.log("#Got file reader:" + myReader);


            if (!file) return Promise.reject(null);
            let reader = new FileReader();
            let processPromise = new Promise((resolve, reject) => {
                reader.onload = (e: ProgressEvent) => {
                    resolve((<any>e.target).result);
                }
            });
            reader.readAsBinaryString(file);
            processPromise.then((data) => {
                this.tncBytes = data;
                // var url = 'data:application/pdf;base64,' + data;
                var url = '' + data;
                this.tncPdfDataString1 = data;
                // this.imageURL = this._santizer.bypassSecurityTrustHtml(url);
                console.log("#tnc data:" + data);
                this.tncPdfDataString = url;
                this.tncPdfDataString = btoa(this.tncPdfDataString);
                console.log("#image data in base64 format:" + this.tncPdfDataString);
                this.tncURL = 'data:application/pdf;base64,' + this.tncPdfDataString;
                this.isTncExists = true;
            }).catch((ex) => {
                console.error('Error fetching users', ex);
            });
        }
    }

    fileChangeEvent(inputValue: any) {
        let test123: any;
        console.log('uploadFile::' + inputValue.target.files);
        this.logoFilesToUpload = <Array<File>>inputValue.target.files;

        for (var i = 0; i < this.logoFilesToUpload.length; i++) {
            console.log("uploads[]", this.logoFilesToUpload[i], this.logoFilesToUpload[i].name);
            let file: File = this.logoFilesToUpload[i];
            console.log("#got file:" + file);
            let myReader: FileReader = new FileReader();
            console.log("#Got file reader:" + myReader);


            if (!file) return Promise.reject(null);
            let reader = new FileReader();
            let processPromise = new Promise((resolve, reject) => {
                reader.onload = (e: ProgressEvent) => {
                    resolve((<any>e.target).result);
                }
            });
            reader.readAsBinaryString(file);
            processPromise.then((data) => {
                this.logoBytes = data;
                // var url = 'data:image/png;base64,' + data;
                var url = '' + data;
                // this.imageURL = this._santizer.bypassSecurityTrustHtml(url);
                console.log("#image data:" + data);
                this.imageDataString = url;
                this.imageDataString = btoa(this.imageDataString);
                console.log("#image data in base64 format:" + this.imageDataString);
                this.imageURL = 'data:image/png;base64,' + this.imageDataString;
            }).catch((ex) => {
                console.error('Error fetching users', ex);
            });
        }

    }


    updateCountry(event: any) {
        console.log("#changedCountry:" + event.value);
    }


    updateBusinessSegment(event: any) {
        console.log("#changedBusinessSegment:" + event.value);
    }

    string2Bin(str: string) {
        var result = [];
        for (var i = 0; i < str.length; i++) {
            result.push(str.charCodeAt(i));
        }
        return result;
    }

    // bin2String(array: any[]) {
    //     return String.fromCharCode.apply(String, array);
    // }

    bin2string(array: any) {
        var result = "";
        for (var i = 0; i < array.length; ++i) {
            result += (String.fromCharCode(array[i]));
        }
        return result;
    }

    // string2Bin('foo'); // [102, 111, 111]
    // bin2String(string2Bin('foo')) === 'foo'; // true


}