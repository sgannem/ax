import { Component, OnInit, EventEmitter, Host } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import { AddNewCardPartitionMediumComponent } from '../add-new-card-partition-medium/add-new-card-partition-medium.component';

@Component({
    moduleId: module.id,
    selector: 'partition-medium-card-layout',
    templateUrl: 'partition-medium-card-layout.component.html',
    styleUrls: ['partition-medium-card-layout.component.scss']
})

export class PartitionMediumCardLayoutComponent implements OnInit {

    private $:JQueryStatic;
    private _cardSize: number;
    private _reservedSpace: number;

    private _sharedSlotCount: number;
    private _sharedSlotSizeEach: number;
    private _sharedSlotBoxWidth: number;

    private _dedicatedSlotCount: number;
    private _dedicatedSlotSizeEach: number;
    private _dedicatedSlotBoxWidth: number;

    private _availableSpaceOnCard: number;
    private _percentageOfAppsThatCanBeInstalledShared: number;
    private _percentageOfAppsThatCanBeInstalledDedicated: number;

    private _sharedSlotMaxList: Array<number> = [];
    private _dedicatedSlotMaxList: Array<number> = [];

    private _master: AddNewCardPartitionMediumComponent;

    /* This array of map represents memory available on card in bytes along with percentage of applicatons that can be installed with this memory available. */
    private _cardMemoryRange: Array<{memory_available: number, percentage_apps_install: number}> = [];

    constructor(private _fb: FormBuilder, @Host() _master: AddNewCardPartitionMediumComponent) {

        this._master = _master;
        this.syncDownPartitionSettings();

        this._cardMemoryRange.push({memory_available: 256, percentage_apps_install: 25});
        this._cardMemoryRange.push({memory_available: 512, percentage_apps_install: 50});
        this._cardMemoryRange.push({memory_available: 1024, percentage_apps_install: 75});
        this._cardMemoryRange.push({memory_available: 2048, percentage_apps_install: 80});
        this._cardMemoryRange.push({memory_available: 4096, percentage_apps_install: 95});
        this._cardMemoryRange.push({memory_available: 8192, percentage_apps_install: 100});
    }

    ngOnInit() {
        
        let timer = Observable.timer(2000, 1000);
        timer.subscribe(t=> {
            this.syncDownPartitionSettings();
        });

        for(var i=1;i<=this._master.getSharedSlotListSize();i++) {
            this._sharedSlotMaxList.push(i);
        }

        for(var i=1;i<=this._master.getDedicatedSlotListSize();i++) {
            this._dedicatedSlotMaxList.push(i);
        }
    }

    private getQualifyingSlotForAvailableSpace(_slotSpaceSizeEach: number, _cardMemoryRange: Array<{memory_available: number, percentage_apps_install: number}>): number {

        let _percentageOfThatCanBeInstalled: number = 0;

        for(let i:number = 0;i<_cardMemoryRange.length;i++) {
            if(_slotSpaceSizeEach>=_cardMemoryRange[i].memory_available) {
                _percentageOfThatCanBeInstalled = _cardMemoryRange[i].percentage_apps_install;
            }
        }
        return _percentageOfThatCanBeInstalled;
    }

    public syncDownPartitionSettings() {

        this._cardSize = this._master.getCardSize();
        this._reservedSpace = this._master.getReservedSpace();

        this._sharedSlotCount = this._master.getSharedSlotCount();
        this._sharedSlotSizeEach = this._master.getSharedSlotSizeEach();
        this._sharedSlotBoxWidth = 100/this._sharedSlotCount;

        this._dedicatedSlotCount = this._master.getDedicatedSlotCount();
        this._dedicatedSlotSizeEach = this._master.getDedicatedSlotSizeEach();
        this._dedicatedSlotBoxWidth = 100/this._dedicatedSlotCount;

        this._availableSpaceOnCard = this._cardSize - this._reservedSpace - (this._sharedSlotCount * this._sharedSlotSizeEach) - (this._dedicatedSlotCount * this._dedicatedSlotSizeEach);
        
        this._percentageOfAppsThatCanBeInstalledShared = this.getQualifyingSlotForAvailableSpace(this._sharedSlotSizeEach, this._cardMemoryRange);
        this._percentageOfAppsThatCanBeInstalledDedicated = this.getQualifyingSlotForAvailableSpace(this._dedicatedSlotSizeEach, this._cardMemoryRange);
    }

    private decrementSharedSlot(e: any): void {

        if(this._master.getSharedSlotCount()>0) {
            this.setSharedSlotCount(this._master.getSharedSlotCount()-1);
            if(this._master.getSharedSlotCount()==0) {
                this.setSharedWidgetClosed(true);
            }
        }
        setTimeout("$('#deleteSharedSlotModal').modal('hide');", 100);
    }

    private decrementDedicatedSlot(e: any): void {

        if(this._master.getDedicatedSlotCount()>0) {
            this.setDedicatedSlotCount(this._master.getDedicatedSlotCount()-1);
            if(this._master.getDedicatedSlotCount()==0) {
                this.setDedicatedWidgetClosed(true);
            }
        }
        setTimeout("$('#deleteDedicatedSlotModal').modal('hide');", 100);
    }

    /* Model methods starts */
    public setSharedSlotCount(slotCount: number) {
        this._master.setSharedSlotCount(slotCount);
    }

    public setDedicatedSlotCount(slotCount: number) {
        this._master.setDedicatedSlotCount(slotCount);
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
    /* Model methods ends */

}
