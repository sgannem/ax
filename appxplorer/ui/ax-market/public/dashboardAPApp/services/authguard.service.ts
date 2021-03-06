import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router) { }

    canActivate() {
       //alert('apUser '+sessionStorage.getItem('apUser'));
        if (sessionStorage.getItem('apUser')) {
            // logged in so return true
            return true;
        }

        // not logged in so redirect to login page
        this.router.navigate(['/login-parent']);
        return false;
    }
}