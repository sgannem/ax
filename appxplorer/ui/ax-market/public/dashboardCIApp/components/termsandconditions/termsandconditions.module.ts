// Angular Imports
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

// This Module's Components
import { TermsAndConditionsComponent } from '../termsandconditions/termsandconditions.component';

@NgModule({
    imports: [],
    declarations: [
        TermsAndConditionsComponent,
    ],
    exports: [
        TermsAndConditionsComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TermsAndConditionsModule {

}
