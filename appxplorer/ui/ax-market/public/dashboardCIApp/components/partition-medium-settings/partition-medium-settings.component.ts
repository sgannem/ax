import { Component, Input, OnInit, Host } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { PartitionMediumValidator } from './validators/partition-medium.validator';

import { AddNewCardPartitionMediumComponent } from '../add-new-card-partition-medium/add-new-card-partition-medium.component';

@Component({
    moduleId: module.id,
    selector: 'partition-medium-settings',
    templateUrl: 'partition-medium-settings.component.html',
    styleUrls: ['partition-medium-settings.component.scss'],
    providers: [PartitionMediumValidator]
})

export class PartitionMediumSettingsComponent {
    
    $:JQueryStatic;
    @Input("card-size") cardSize: number;

    private _master: AddNewCardPartitionMediumComponent;
    partitionMediumForm : FormGroup;
    _maxLimitForReservedSpace: number;
    _reservedSpace: number;

    constructor(private _fb: FormBuilder, @Host() _master: AddNewCardPartitionMediumComponent) {
        this._master = _master;
    }

    ngOnInit() {

        this.cardSize = this._master.getCardSize();
        this._maxLimitForReservedSpace = this.getAvailableSpaceForReservedSpace();
        this.partitionMediumForm = this._fb.group({
            reservedSpace: new FormControl('', [PartitionMediumValidator.validateReservedSpace(this._maxLimitForReservedSpace)]),
        });

        /* Setting committed value stored for Confirm screen when user visits back to Partition Medium screen. */
        this.partitionMediumForm.controls['reservedSpace'].setValue(this.getReservedSpace(), {onlySelf: true});
        this._reservedSpace = this.getReservedSpace()
    }

    public updateReservedSpaceToMaster(e: any): void {

        /* Updating validation for Reserved Space with latest values as validation set in ngOnInit takes Max limit for Available space on card for Reserved Space only at the point of component initialization, but does not update later. */
        this._maxLimitForReservedSpace = this.getAvailableSpaceForReservedSpace();
        this.partitionMediumForm.controls['reservedSpace'].setValidators([PartitionMediumValidator.validateReservedSpace(this.getAvailableSpaceForReservedSpace())]);


        if(this.isValidReservedSpace()) {
            let _reservedSpace: number = this.partitionMediumForm.get('reservedSpace').value;
            if(_reservedSpace==undefined || _reservedSpace<=0) {
                _reservedSpace = 0;
            }
            this.setReservedSpace(_reservedSpace);
            this._reservedSpace = _reservedSpace;
        }
    }

    private isValidReservedSpace() {

        let _isValidReservedSpace: boolean = false;
        let _reservedSpaceValue: number = this.partitionMediumForm.get('reservedSpace').value;
        if(_reservedSpaceValue==undefined || _reservedSpaceValue<=0) {
            _reservedSpaceValue = 0;
        }

        let _availableSpace: number = this.getAvailableSpaceForReservedSpace();

        if(_reservedSpaceValue<=_availableSpace) {
            _isValidReservedSpace = true;
        }

        return _isValidReservedSpace;

    }

    /* Model functions start */
    public getCardSize() {
        return this._master.getCardSize();
    }

    public getReservedSpace() {
        return this._master.getReservedSpace();
    }

    public setReservedSpace(reservedSpace: number) {
        return this._master.setReservedSpace(reservedSpace);
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

    public getAvailableSpaceForReservedSpace() {
        let _availableSpace: number = this.getCardSize() - (this.getSharedSlotCount() * this.getSharedSlotSizeEach()) - (this.getDedicatedSlotCount() * this.getDedicatedSlotSizeEach());
        return _availableSpace;
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
