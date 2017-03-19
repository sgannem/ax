// Angular Imports
import { NgModule } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {BrowserModule} from '@angular/platform-browser';

// This Module's Components
import { AccountComponent } from '../account/account.component';

@NgModule({
   imports: [ BrowserModule,
    ReactiveFormsModule,
    HttpModule ],
    declarations: [
        AccountComponent,
    ],
    exports: [
        AccountComponent,
    ]
})
export class AccountModule {

}
