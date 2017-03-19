import { Component } from '@angular/core';
import { RouterModule, Routes, Router } from '@angular/router';

@Component({
    moduleId: module.id,
    selector: 'dashboardhome-topnavigation',
    templateUrl: 'dashboardhome-topnavigation.component.html',
    styleUrls: ['dashboardhome-topnavigation.component.css']
})

export class DashboardHomeTopNavigationComponent {

    constructor(private router: Router) { }

    /** this method invokes when user clicks update details */
    updateUserDetails(event:any) {
        console.log("link clicked......"+event);
        console.log("router:"+this.router);
        this.router.navigate(['/show-account']);
    }

}
