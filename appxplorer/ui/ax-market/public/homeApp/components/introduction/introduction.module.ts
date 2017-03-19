// Angular Imports
import { NgModule } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';

// This Module's Components
import { IntroductionComponent } from './introduction.component';

@NgModule({
    imports: [ BrowserModule ],
    declarations: [ IntroductionComponent ],
    exports: [ IntroductionComponent ]
})
export class IntroductionModule {

}
