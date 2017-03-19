import { Component, OnInit } from '@angular/core';
import { HttpAccountService } from '../../services/account.service';
import { FormBuilder, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { FormControl, FormGroup } from '@angular/forms';
import { AccountDetails } from './accountdetails';
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
    moduleId: module.id,
    selector: 'account',
    templateUrl: 'account.component.html',
    styleUrls: ['account.component.css'],
    providers: [HttpAccountService]
})

export class AccountComponent implements OnInit {
    public myForm: FormGroup;
    public submitted: boolean;
    public events: any[] = [];

    constructor(private _fb: FormBuilder, private avd: HttpAccountService, private _flashMessagesService: FlashMessagesService) { }

    ngOnInit() {

        // 1st parameter is a flash message text
        // 2nd parameter is optional. You can pass object with options.
        console.log('#displaying flash message');
        //this._flashMessagesService.show('We are in Account component!', { cssClass: 'alert-success', timeout: 1000 });

        // the long way
        // this.myForm = new FormGroup({
        //     name: new FormControl('', [<any>Validators.required, <any>Validators.minLength(5)]),
        //     address: new FormGroup({
        //         address1: new FormControl('', <any>Validators.required),
        //         postcode: new FormControl('8000')
        //     })
        // })

        console.log("-----------------------------");
        console.log(this.avd.getBasicAccountDetails());
        this.showErrorMessage('please fill all the account details for getting reviewed by nxp');

        // the short way
        this.myForm = this._fb.group({
            // name: ['', [<any>Validators.required, <any>Validators.minLength(5)]],
            // address: this._fb.group({
            //     street: ['', <any>Validators.required],
            //     postcode: ['8000']
            // })

            // firstName: ['', [<any>Validators.required, <any>Validators.minLength(5)]],
            // lastName: ['', [<any>Validators.required, <any>Validators.minLength(5)]],
            // email: ['', [<any>Validators.required, <any>Validators.minLength(5)]],
            // password: ['', [<any>Validators.required, <any>Validators.minLength(5)]],
            // companyName: ['', [<any>Validators.required, <any>Validators.minLength(5)]]

            firstName: [''],
            lastName: [''],
            email: [''],
            password: [''],
            companyName: ['']

            // })
        });

        // subscribe to form changes  
        this.subcribeToFormChanges();

        // Update single value
        (<FormControl>this.myForm.controls['email'])
            .setValue('gsrini.in@gmail.com', { onlySelf: true });

        (<FormControl>this.myForm.controls['password'])
            .setValue('123456', { onlySelf: true });

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

    }

    subcribeToFormChanges() {
        const myFormStatusChanges$ = this.myForm.statusChanges;
        const myFormValueChanges$ = this.myForm.valueChanges;

        myFormStatusChanges$.subscribe(x => this.events.push({ event: 'STATUS_CHANGED', object: x }));
        myFormValueChanges$.subscribe(x => this.events.push({ event: 'VALUE_CHANGED', object: x }));
    }

    isDisabled(link) {
        return link === 'Other';
    }

    save(model: AccountDetails, isValid: boolean) {
        this.submitted = true;
        console.log(model, isValid);
        this.showSuccessMessage("Account details are saved successfully");
    }

    showSuccessMessage(message:string) {
        this._flashMessagesService.show(message, { cssClass: 'alert-success', timeout: 2500 });
    }

    showErrorMessage(message:string) {
        this._flashMessagesService.show(message, { cssClass: 'alert-danger', timeout: 2500 });
    }


}