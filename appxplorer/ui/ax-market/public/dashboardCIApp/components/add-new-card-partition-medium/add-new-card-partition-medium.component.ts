import { Component, Host, OnInit } from '@angular/core';
import { AddNewCardNavigationComponent } from '../add-new-card-navigation/add-new-card-navigation.component';

@Component({
    moduleId: module.id,
    selector: 'add-new-card-partition-medium',
    templateUrl: 'add-new-card-partition-medium.component.html',
    styleUrls: ['add-new-card-partition-medium.component.scss']
})

export class AddNewCardPartitionMediumComponent implements OnInit {

    private _master: AddNewCardNavigationComponent;

    constructor(@Host() _master: AddNewCardNavigationComponent) {
        this._master = _master;
    }

    ngOnInit() {

    }

    /* Model functions start */
    public getCardSize() {
        return this._master.getCardSize();
    }

    public getReservedSpace() {
        return this._master.getReservedSpace();
    }

    public setReservedSpace(reservedSpace: number) {
        this._master.setReservedSpace(reservedSpace);
    }

    public getSharedSlotCount() {
        return this._master.getSharedSlotCount();
    }

    public setSharedSlotCount(sharedSlotCount: number) {
        this._master.setSharedSlotCount(sharedSlotCount);
    }

    public getSharedSlotSizeEach() {
        return this._master.getSharedSlotSizeEach();
    }

    public setSharedSlotSizeEach(sharedSlotSizeEach: number) {
        this._master.setSharedSlotSizeEach(sharedSlotSizeEach);
    }

    public getDedicatedSlotCount() {
        return this._master.getDedicatedSlotCount();
    }

    public setDedicatedSlotCount(dedicatedSlotCount: number) {
        this._master.setDedicatedSlotCount(dedicatedSlotCount);
    }

    public getDedicatedSlotSizeEach() {
        return this._master.getDedicatedSlotSizeEach();
    }

    public setDedicatedSlotSizeEach(dedicatedSlotSizeEach: number) {
        this._master.setDedicatedSlotSizeEach(dedicatedSlotSizeEach);
    }
    
    public getSharedSlotListSize() {
        return this._master.getSharedSlotListSize();
    }
    
    public getDedicatedSlotListSize() {
        return this._master.getDedicatedSlotListSize();
    }
    
    public getSharedWidgetClosed() {
        return this._master.getSharedWidgetClosed();
    }
    
    public setSharedWidgetClosed(sharedSlotCloseWidget: boolean) {
        this._master.setSharedWidgetClosed(sharedSlotCloseWidget);
    }
    
    public getDedicatedWidgetClosed() {
        return this._master.getDedicatedWidgetClosed();
    }
    
    public setDedicatedWidgetClosed(dedicatedSlotCloseWidget: boolean) {
        this._master.setDedicatedWidgetClosed(dedicatedSlotCloseWidget);
    }
    /* Model functions end */

    
}
