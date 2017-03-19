import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    moduleId: module.id,
    selector: 'add-new-card-navigation',
    templateUrl: 'add-new-card-navigation.component.html',
    styleUrls: ['add-new-card-navigation.component.scss']
})

export class AddNewCardNavigationComponent {

    isFirstPage: boolean = true;
    isLastPage: boolean = false;
    
    prevPage: number = 0;
    nextPage: number = 1;

    /* Technical details component have to update captured card size here */
    private cardSize: number;
    private reservedSpace: number;
    
    private sharedSlotCount: number;
    private sharedSlotSizeEach: number;
    private dedicatedSlotCount: number;
    private dedicatedSlotSizeEach: number;

    private sharedSlotListSize: number;
    private dedicatedSlotListSize: number;

    private sharedSlotCloseWidget: boolean;
    private dedicatedSlotCloseWidget: boolean;

    routerLinks: string[] = ['/show-mycards/show-add-new-card/show-add-new-card-nav/show-add-new-card-basic-information',
                            '/show-mycards/show-add-new-card/show-add-new-card-nav/show-add-new-card-technical-details',
                            '/show-mycards/show-add-new-card/show-add-new-card-nav/show-add-new-card-partition-medium',
                            '/show-mycards/show-add-new-card/show-add-new-card-nav/show-add-new-card-confirm'];

    constructor(private _router: Router) {

        this.cardSize = 8192;
        this.reservedSpace = 0;

        this.sharedSlotCount = 0;
        this.sharedSlotSizeEach = 0;

        this.dedicatedSlotCount = 0;
        this.dedicatedSlotSizeEach = 0;

        this.sharedSlotListSize = 5;
        this.dedicatedSlotListSize = 5;

        this.sharedSlotCloseWidget = false;
        this.dedicatedSlotCloseWidget = false;
    }

    navigateToPage(pageIndex: number) {

        if(this.prevPage<0) {
            this.prevPage = 0;
        }

        if(this.nextPage>=this.routerLinks.length) {
            this.nextPage = this.routerLinks.length - 1;
        }

        this.prevPage = pageIndex-1;
        this.nextPage = pageIndex+1;

        if(pageIndex<=0) {
            this.isFirstPage = true;
            this.isLastPage = false;
        }

        if(pageIndex>0) {
            this.isFirstPage = false;
            this.isLastPage = false;
        }

        if(pageIndex>=(this.routerLinks.length-1)) {
            this.isLastPage = true;
        }
        
        this._router.navigateByUrl(this.routerLinks[pageIndex]);

    }

    /* Model functions start */
    public getCardSize() {
        return this.cardSize;
    }

    public getReservedSpace() {
        return this.reservedSpace;
    }

    public setReservedSpace(reservedSpace: number) {
        this.reservedSpace = reservedSpace;
    }

    public getSharedSlotCount() {
        return this.sharedSlotCount;
    }

    public setSharedSlotCount(sharedSlotCount: number) {
        this.sharedSlotCount = sharedSlotCount;
    }

    public getSharedSlotSizeEach() {
        return this.sharedSlotSizeEach;
    }

    public setSharedSlotSizeEach(sharedSlotSizeEach: number) {
        this.sharedSlotSizeEach = sharedSlotSizeEach;
    }

    public getDedicatedSlotCount() {
        return this.dedicatedSlotCount;
    }

    public setDedicatedSlotCount(dedicatedSlotCount: number) {
        this.dedicatedSlotCount = dedicatedSlotCount;
    }

    public getDedicatedSlotSizeEach() {
        return this.dedicatedSlotSizeEach;
    }

    public setDedicatedSlotSizeEach(dedicatedSlotSizeEach: number) {
        this.dedicatedSlotSizeEach = dedicatedSlotSizeEach;
    }

    public getSharedSlotListSize() {
        return this.sharedSlotListSize;
    }

    public getDedicatedSlotListSize() {
        return this.dedicatedSlotListSize;
    }
    
    public getSharedWidgetClosed() {
        return this.sharedSlotCloseWidget;
    }
    
    public setSharedWidgetClosed(sharedSlotCloseWidget: boolean) {
        this.sharedSlotCloseWidget = sharedSlotCloseWidget;
    }
    
    public getDedicatedWidgetClosed() {
        return this.dedicatedSlotCloseWidget;
    }
    
    public setDedicatedWidgetClosed(dedicatedSlotCloseWidget: boolean) {
        this.dedicatedSlotCloseWidget = dedicatedSlotCloseWidget;
    }
    /* Model functions end */

}
